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
var handlers = {
    'NewsRequest': function () {
        var result = Rokid.sync_request('GET', 'https://www.toutiao.com/hot_words/'), ttsRes = '';
        result = Rokid.resHandler(result);
        result.forEach(function (item) {
            ttsRes += item + ',';
        });
        this.emit(':tts', { tts: ttsRes });
        this.callback(null);
    }
};

//以上为同步js脚本demo

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
                self.callback(error);
            }
            JSON.parse(body).forEach(function (item) {
                ttsRes += item + ',';
            });
            self.emit(':tts', { tts: ttsRes });
            self.callback(null);
        });
    }
};

//以上为异步js脚本demo