exports.handler = function (event, context, callback) {
    var rokid = Rokid.handler(event, context, callback);
    rokid.registerHandlers(handlers);
    rokid.execute();
};

var handlers = {
    'ROKID.INTENT.WELCOME': function () {
        try{
            this.emit(':tts', { tts: '您好，请问有什么可以帮您。' });
            this.callback(null);
        }catch(e){
            this.callback(e);
        }
    },
    'bestcoffeebar': function () {
        try{
            this.emit(':tts', { tts: '我看位于问溪路89号的米萨咖啡就很不错。' });
            this.callback(null);
        }catch(e){
            this.callback(e);
        }
    },
    'nicedrink': function () {
        try{
            this.emit(':tts', { tts: '只要是咖啡都很好喝。' });
            this.callback(null);
        }catch(e){
            this.callback(e);
        }
    }
};
