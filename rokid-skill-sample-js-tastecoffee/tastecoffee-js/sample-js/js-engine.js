
exports.handler = function (event, context, callback) {
    var rokid = Rokid.handler(event, context, callback);
    rokid.registerHandlers(handlers);
    rokid.execute();
};

var handlers = {
    'ROKID.INTENT.WELCOME': function () {
        try {
            this.setTts({ tts: '您好，请问有什么可以帮您。' });
            this.emit(':done');
        } catch (error) {
            this.emit(':error', error);
        }
    },
    'bestcoffeebar': function () {
        try {
            this.setTts({ tts: '我看位于问溪路89号的米萨咖啡就很不错。' });
            this.emit(':done');
        } catch (error) {
            this.emit(':error', error);
        }
    },
    'nicedrink': function () {
        try {
            this.setTts({ tts: '只要是咖啡都很好喝。' });
            this.emit(':done');
        } catch (error) {
            this.emit(':error', error);
        }
    }
};
