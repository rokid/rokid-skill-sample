{
    "intents": [
        {
            "intent": "MediaRequest",
            "slots": [],
            "user_says": [
                "播放"
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
        try{
            this.emit(':media', { itemType: 'AUDIO', url: 's.rokidcdn.com/temp/rokid-ring.mp3' });
            this.callback(null);
        }catch(e){
            this.callback(e);
        }
    }
};

//以上为js脚本demo