//==================================================================config======================================================================
//问题集
var questions =[
    {'name':'answer1','quotation':'question1'},  
    {'name':'answer2','quotation':'question2'},  
    {'name':'answer3','quotation':'question3'}  
];
//问题播放类型 1:TTS 2:Media
var questionPlayType=1;
//游戏名
var gameName="农药英雄竞猜";
//最多错误次数
var maxErrorCount=3;
//单题错误次数
var maxSingleErrorCount=2;
//游戏等级
var gradeList=['青铜','白银','黄金','铂金','钻石','最强王者','荣耀王者'];
//升级所需积分
var singleGradeCount=5;

//流程TTS模板 {}部分可以删不能增加,其他部分任意修改
//欢迎语
var welcomeTemplate='欢迎来到{gameName},我会给出英雄语录,告诉我对应的英雄名字,你一共只有{maxErrorCount}次回答错误的机会，准备好了的话请对我说开始挑战';
//答题引导语
var guideTemplate='听声音,告诉我这是哪个英雄';
//单次回答错误
var singleErrorTemplate='很遗憾,回答错误,你可以再答一次';
//回答正确
var rightTemplate='恭喜你答对了,请听下一题。{nextQuestion}';
//回答错误
var errorTemplate='回答错误,正确答案是,{rightAnswer},请听下一题。{nextQuestion}';
//我不知道
var unknownTemplate='正确答案是,{rightAnswer},请听下一题';
//游戏结束
var gameOverTemplate='正确答案是,{rightAnswer},游戏结束,共答对{rightCount}题,你的段位是{grade}.你可以对我说重新开始';
//游戏通关
var completeTemplate='恭喜你已经通关,共答对{rightCount}题,你的段位是{grade}';
//提示游戏已开始
var startedTemplate='游戏已开始,你可以告诉我英雄名';
//提示游戏未开始
var unStartTemplate='游戏还没开始,你可以对我说开始';
//==================================================================private variable======================================================================

// //错误列表
// var errorList=[];
// //正确列表
// var rightList=[];
// //未使用题目(记录questions的index)
// var unusedList=[];
// //状态： 0:未开始 1:游戏中
// var state=0;
// //单次回答错误次数
// var errorCount=0;
// //当前问题脚标
// var questionIndex=0;
// //当前未使用题目列表脚标
// var unusedIndex=0;
var gameData={
    "errorList":[],
    "rightList":[],
    "unusedList":[],
    "state":0,
    "singleErrorCount":0,
    "questionIndex":0,
    "unusedIndex":0
}


//==================================================================处理框架======================================================================
function handle(context,execute){
    var userId = Rokid.param.context.user.userId ? Rokid.param.context.user.userId : "test_user_id";
    Rokid.dbServer.get(userId, (error, result) => {
        if(error) {
            context.emit(':error', error);
            return;
        }
        //恢复数据
        recoverData(JSON.parse(result));
        //执行处理方法
        execute(context);

        //保存数据
        saveData2DbServer(context);
    });
}
//恢复数据到全局变量
function recoverData(json){
    gameData=json;
}
//往DB中存数据
function saveData2DbServer(context){
    var userId = Rokid.param.context.user.userId ? Rokid.param.context.user.userId : "test_user_id";
    Rokid.dbServer.set(userId, JSON.stringify(gameData),function(error, result){
        context.emit(':done');
    }); 
}


//==================================================================业务方法======================================================================

//初始化数据
function init(context){
    gameData={
        "errorList":[],
        "rightList":[],
        "unusedList":[],
        "state":0,
        "singleErrorCount":0,
        "questionIndex":0,
        "unusedIndex":0
    };
    for (var index = 0; index < questions.length; index++) {
        gameData.unusedList.push(index);
    };
}

//开始挑战
function begin(context){
    if(!validateState(context,0)){
        return;
    }
    generateNewQuestion(context);     
    gameData.state=1;
    context.setTts({tts:guideTemplate.format()});
    context.setMedia({ type: 'AUDIO', url: questions[gameData.questionIndex].quotation}); 
}
//我不知道
function unknown(context){
    if(!validateState(context,1)){
        return;
    }
    gameData.errorList.push(gameData.questionIndex);
    if(gameData.errorList.length==3){
        gameOver(context);
    }else{
        gameData.unusedList.splice(gameData.unusedIndex,1);
        gameData.singleErrorCount=0;
        context.setTts({tts:unknownTemplate.format({"rightAnswer":questions[gameData.questionIndex].name})});
        generateNewQuestion();
        context.setMedia({ type: 'AUDIO', url: questions[gameData.questionIndex].quotation});
    }
}

//回答问题
function answer(context){
    if(!validateState(context,1)){
        return;
    }
    var answer=Rokid.param.request.content.slots.answer;    
    var right=questions[gameData.questionIndex].name;
    if(answer==right){
        answerRight(context);
    }else{
        answerWrong(context);
    }
}

//回答正确
function answerRight(context){
    gameData.rightList.push(gameData.questionIndex);
    gameData.unusedList.splice(gameData.unusedIndex,1);
    if(gameData.unusedList.length==0){
        context.setTts({tts:completeTemplate.format({"rightCount":rightCount,"grade":getGrade()})});
        init(context);
    }else{
        generateNewQuestion();
        gameData.singleErrorCount=0;
        context.setTts({tts:rightTemplate.format()});
        context.setMedia({ type: 'AUDIO', url: questions[gameData.questionIndex].quotation});
    }
}

//回答错误
function answerWrong(context){
    gameData.singleErrorCount++;
    if(gameData.singleErrorCount<maxSingleErrorCount){
        context.setTts({tts:singleErrorTemplate.format()});
    }else{
        gameData.errorList.push(gameData.questionIndex);
        if(gameData.errorList.length>=maxErrorCount){
            gameOver(context);
        }else{
            gameData.unusedList.splice(gameData.unusedIndex,1);
            gameData.singleErrorCount=0;
            context.setTts({tts:errorTemplate.format({"rightAnswer":questions[gameData.questionIndex].name})});
            generateNewQuestion();
            context.setMedia({ type: 'AUDIO', url: questions[gameData.questionIndex].quotation});
        }
    }
}

//游戏结束
function gameOver(context){
    context.setTts({tts:gameOverTemplate.format({"rightAnswer":questions[gameData.questionIndex].name,"rightCount":rightCount,"grade":getGrade()})});
    init(context);
}

//校验状态
function validateState(context,expect){
    if(expect==gameData.state){
        return true;
    }
    if(expect==0&&gameData.state==1){
        context.setTts({tts:startedTemplate});
    }
    if(expect==1&&gameData.state==0){
        context.setTts({tts:unStartTemplate});
    }
    return false;
}

//随机获得一个未使用问题的脚标
function getUnUsedQuestionIndex(){
    return randomInt(gameData.unusedList.length);
}

// 生成新问题
function generateNewQuestion(context){
    gameData.unusedIndex=getUnUsedQuestionIndex();
    gameData.questionIndex=gameData.unusedList[gameData.unusedIndex];
}

function getGrade(){
    var rightCount=gameData.rightList.length;
    var grade=gradeList[Math.min(parseInt(rightCount/singleGradeCount),gradeList.length-1)];
    return grade;
}
//==================================================================method tool======================================================================
//生成0~maxInt之间的随机整数
function randomInt(maxInt){
    return Math.floor(Math.random()*(maxInt+1));
}
//字符串格式化
String.prototype.format = function(args) {
    var result = this;
    if (arguments.length < 1) {
        return result;
    }

    var data = arguments;
    if (arguments.length == 1 && typeof (args) == "object") {
        data = args;
    }
    for (var key in data) {
        var value = data[key];
        if (undefined != value) {
            result = result.replace("{" + key + "}", value);
        }
    }
    return result;
}

//==================================================================注册======================================================================
exports.handler = function(event, context, callback) {
    var rokid = Rokid.handler(event, context,callback);
    rokid.registerHandlers(handlers);
    rokid.execute();
};
        
var handlers = {
    'ROKID.INTENT.WELCOME':function() {
        try {
            init(this);
            this.setTts({tts:welcomeTemplate.format({"gameName":gameName,"maxErrorCount":maxErrorCount})});
            saveData2DbServer(this);
        } catch (error) {
            this.emit(':error', error);
        }
    },
    'begin':function(){
        var self = this;
        try {
            handle(self,begin);
        } catch (error) {
            self.emit(':error', error);
        }        
    },
    'answer':function() {
        var self=this;
        try {
            handle(self,answer);
        } catch (error) {
            self.emit(':error', error);
        }
    },
    'unknown':function(){
        var self=this;
        try {
            handle(self,unknown);
        } catch (error) {
            self.emit(':error', error);
        }
    }
};
