{
    "intents": [
        {
            "intent": "set",
            "slots": [],
            "user_says": [
                "保存"
            ]
        },
        {
            "intent": "get",
            "slots": [],
            "user_says": [
                "获取"
            ]
        },
        {
            "intent": "delete",
            "slots": [],
            "user_says": [
                "删除"
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
    'set': function () {
        try {
            Rokid.dbServer.set('key', 'value', (error, res) => {
                if (error) {
                    this.emit(':error', error);
                } else {
                    this.setTts({ tts: res });
                    this.emit(':done');
                }
            });
        } catch (error) {
            this.emit(':error', error);
        }
    },
    'get': function () {
        try {
            Rokid.dbServer.get('key', (error, res) => {
                if (error) {
                    this.emit(':error', error);
                } else {
                    this.setTts({ tts: res });
                    this.emit(':done');
                }
            });
        } catch (error) {
            this.emit(':error', error);
        }
    },
    'delete': function () {
        try {
            Rokid.dbServer.delete('key', (error, res) => {
                if (error) {
                    this.emit(':error', error);
                } else {
                    this.setTts({ tts: res });
                    this.emit(':done');
                }
            });
        } catch (error) {
            this.emit(':error', error);
        }
    }
};

//以上为js脚本demo