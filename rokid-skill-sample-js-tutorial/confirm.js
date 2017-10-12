{
    "intents": [
        {
            "intent": "confirmRequest",
            "slots": [],
            "user_says": [
                "多轮交互"
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
    'confirmRequest': function () {
        try {
            this.setTts({tts: '欢迎成为Rokid开发者' });
            this.setConfirm({
                confirmIntent: 'question_one',
                confirmSlot: 'question_one_slot'
            })
            this.emit(':done');
        } catch (error) {
            this.emit(':error', error);
        }
    }
};

//以上为js脚本demo