{
    "intents": [
        {
            "intent": "cardRequest",
            "slots": [],
            "user_says": [
                "用户登录"
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
    'cardRequest': function () {
        try {
            this.setTts({tts: '请到手机上登录。' });
            this.setCard('ACCOUNT_LINK');
            this.emit(':done');
        } catch (error) {
            this.emit(':error', error);
        }
    }
};

//以上为js脚本demo