{
    "intents": [
        {
            "intent": "NewsRequest",
            "slots": [],
            "user_says": [
                "今日头条热词"
            ]
        }
    ]
}

//以上为语音交互配置

exports.handler = function (event, context, callback) {
    var rokid = Rokid.handler(event, context, callback);
    rokid.registerHandlers(handlers);
    rokid.execute();
};
var options = {
    method: 'GET',
    url: 'https://www.toutiao.com/hot_words/',
    headers:
    {
        'User-Agent': 'request'
    }
};
var handlers = {
    'NewsRequest': function () {
        var ttsRes = '', self = this;
        Rokid.request(options, function (error, response, body) {
            if (error) {
                self.emit(':error', error);
            }
            JSON.parse(body).forEach(function (item) {
                ttsRes += item + ',';
            });
            self.setTts({ tts: ttsRes });
            self.emit(':done');
        });
    }
};

//以上为异步js脚本demo