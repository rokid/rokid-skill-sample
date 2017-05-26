{
    "intents": [
        {
            "intent": "NewsRequest",
            "slots": [],
            "user_says": [
                "放首诗词"
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
        var result = Rokid.sync_request('GET', 'https://www.toutiao.com/hot_words/'),ttsRes = '';
        result = Rokid.resHandler(result);
        result.forEach(function(item){
            ttsRes += item + ','
        })
        this.emit(':tts', { tts: ttsRes });
    }
}

//以上为js脚本demo