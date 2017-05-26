{
    "intents": [
        {
            "intent": "MediaRequest",
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
    'MediaRequest': function () {
        this.emit(':media', { itemType: 'AUDIO', url: 's.rokidcdn.com/temp/rokid-ring.mp3' });
    }
}

//以上为js脚本demo