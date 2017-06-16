{
    "intents": [
        {
            "intent": "HELLO",
            "slots": [],
            "user_says": [
                "你好"
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
    'HELLO': function () {
        try{
            this.emit(':tts', { tts: 'HELLO WORLD' });
            this.callback(null);
        }catch(e){
            this.callback(e);
        }
    }
};

//以上为js脚本demo