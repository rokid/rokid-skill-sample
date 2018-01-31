# 技能开发说明文档（Java版） #
***

**您将学会** 怎么创建一个技能、使用Java语言，借助rokid-skill-kit-java开发一个技能。
#### 您需要掌握
基本的Java开发技能，相关开发基于Eclipse IDE，简单的 Git 使用技巧。
#### 您需要准备

1. [Rokid开发者账号](https://developer.rokid.com/#/)  
2. 在 GitHub 中下载 [rokid-skill-sample-java](https://github.com/Rokid/rokid-skill-sample/tree/master/rokid-skill-sample-java)源码，其中主要包括三部分：
-  [rokid-skill-tastecoffee](https://github.com/Rokid/rokid-skill-sample/tree/master/rokid-skill-sample-java/rokid-skill-tastecoffee)：我要喝咖啡 Demo Java 源码。
-  [rokid-skill-kit-java](https://github.com/Rokid/rokid-skill-sample/tree/master/rokid-skill-sample-java/rokid-skill-kit-java)：Skill 开发工具包。
-  [rokid-skill-protocol](https://github.com/Rokid/rokid-skill-sample/tree/master/rokid-skill-sample-java/rokid-skill-tastecoffee)：Skill 开发协议。
***

### 1.注册Rokid开发者账号

在[Rokid开放平台](https://developer.rokid.com/#/)免费注册一个Rokid开发者账号。
***
### 2.创建一个技能

需要您登陆Rokid开发后台
![](https://github.com/Rokid/rokid-skill-sample/raw/master/rokid-skill-sample-js-tastecoffee/images/14977020395593.jpg)
![](https://github.com/Rokid/rokid-skill-sample/raw/master/rokid-skill-sample-js-tastecoffee/images/14977048666519.jpg)
登录后，选择「技能开发工具」，并点击「创建新技能」。
![](https://github.com/Rokid/rokid-skill-sample/raw/master/rokid-skill-sample-js-tastecoffee/images/14977049108315.jpg)
![](https://github.com/Rokid/rokid-skill-sample/raw/master/rokid-skill-sample-js-tastecoffee/images/14977049770141.jpg)
#### 给你的技能起个名字： ####
 1.技能属性请选择「公开」。  
 2.技能类型请选择「自定义技能」。  
 3.为技能起个好名字。  
 4.为技能起一个朗朗上口的「入口词」，用户将用他来唤起你的技能。
![](https://d26dzxoao6i3hh.cloudfront.net/items/0x2d0V3R10460X2i3R1n/%E5%85%A5%E5%8F%A3%E8%AF%8D.png?v=10cebd71)
*此处的示例技能名称，入口词为「打开我要喝咖啡」或者「开启我要喝咖啡」*

完成后请点击「下一步」。
***
### 3.定义技能的语音交互并进行后端配置 ###
####（1）定义语音交互
接着在「语音交互」页面中，在意图定义中的输入框中填入json格式的意图。json格式数据可参照我们制定的[模板](https://github.com/Rokid/rokid-skill-sample/blob/master/rokid-skill-sample-java/rokid-skill-tastecoffee/voice-interaction/intent.json)，复制里面的内容，放在图中意图定义里面。示例代码：
```json
{
"intents": [
    {
        "intent": "nicedrink",
        "slots": [],
        "user_says": [
            "米萨咖啡最好喝的是哪一种",
            "米萨咖啡哪种最好喝"
        ]
    },
    {
        "intent": "bestcoffeebar",
        "slots": [
            {
                "name": "city",
                "type": "ROKID.CN_CITY_ZH"
            }
        ],
        "user_says": [
            "$city哪里有好一点的咖啡馆",
            "$city哪里有好点的咖啡馆",
            "$city哪里好点的咖啡馆"
        ]
    }
]
}
```




![](https://github.com/Rokid/rokid-skill-sample/raw/master/rokid-skill-sample-js-tastecoffee/images/14977054155308.jpg)
然后点击添加词条，词表内容对应您定义的slots里面的type。
该词表对应user_says里面的city。

下面的值填写您期望的值 例如：杭州，上海。

如果此时未定义city这个词表依赖,“杭州”这个参数将不会输出给您的后端服务。

- **提示：此时可以在该页面右侧的“语音交互测试”中测试配置是否正确。**
- 示例语句：杭州哪里有好一点的咖啡馆。

完成后请点击「下一步」。
####（2）完成服务配置
接着在「配置」页面中，



- 选择HTTPS

- 下面填上您服务部署的地址，格式：https://服务部署的地址/技能名称/speechlet

- 认证key可以选择不填

- 是否需要用户授权：否

- 保存

此时您可以先完善您的技能，接下来是我要喝咖啡示例代码流程演示
***
### 4.下载源码和配置进行技能开发 ###
#### 您需要两个依赖源码和一个示例Demo源码: ####
**rokid-skill-kit-java源码、
rokid-skill-protocol源码、我要喝咖啡的Demo源码**

rokid-skill-kit-java简介：
`skill-kit基于maven管理的一套工具，其依赖于skill-protocol工具。skill-kit是基于SpringMVC框架的一套工具，是为技能开发定义的一组协议。`

rokid-skill-protocol简介：
`Skill-protocol封装了request请求以及response的各种数据model,在protocol.utils下的RequestUtils里面封装了对请求操作的各种工具方法，ResponseBuilder封装了用于媒体控制的各种工具方法，开发者应用这些方法让自己的技能产生效应。`

**准备代码：**

1、rokid-skill-tastecoffee源码
2、rokid-skill-kit-java源码
3、rokid-skill-protocol源码


**编译环境：**   [Eclipse软件](http://www.eclipse.org/downloads/)（下载地址）

**环境配置：** [需要配置JDK和JRE](https://jingyan.baidu.com/article/f96699bb8b38e0894e3c1bef.html)（使用的 JDK 版本为1.7）

**需求配置:**  [下载Maven以及在eclipse中配置和使用](https://jingyan.baidu.com/article/295430f136e8e00c7e0050b9.html)（链接为下载地址）

**源码导入:** eclipse导入源码文件

步骤：

1. File-->Import-->Maven-->Existing Maven Projects-->点击Next

2. 选择源码所在目录
3. 勾选Projects里面的pom.xml文件

4. 点击Finish

重复以上步骤，完成需要源码的导入，完成之后即可在eclipse显示。

**执行操作：** 在两个依赖项目(rokid-skill-kit-java和rokid-skill-protocol)名称上鼠标点击右键-->Run As-->Maven install
***

### 5.开发一个技能 进入工程查看代码 （学会怎么开发一个技能）



**开发技能示例（我要喝咖啡源码）：rokid-skill-tastecoffee**

    可以根据自己需求进行更改项目名称：需要在pom.xml文件中更改相同的名称

**开发自己的技能：**

（1）在您安装好的eclipse中新建maven工程（File-->New-->Other-->Maven-->Maven Project-->点击两次Next-->Artifact Id选择maven-archetype-webapp-->Next-->Artifact Id输入框中输入自己的项目名称-->Finish)。

（2）在您新建的工程中开发一个技能（把Demo中src/main/java中的包全部复制到自己的src/main/java中，这些包都是写一个技能需要的-->把Demo中src/main/resource中的配置文件复制到自己的src/main/resource中，这些配置文件是为了完成技能框架的配置-->把您src/main/resource中config的jdbc.properties文件中的数据库username、password、url更改为自己数据库的用户名、密码和数据库名）-->将Demo中`src/main/webapp`目录下文件拷贝到自身工程的`src/main/webapp`目录下，此步可完成web.xml文件的配置。

（3）pom.xml文件中需要您加入skill-kit的依赖，可参考Demo中的pom.xml文件。

（4）开始编写您的技能（打开src/main/java中的dispatcher包中的SkillDispatch类，找到appStartIntent方法进行编写代码实现您的技能入口词，参考**代码开发示例**中的appStartIntent方法代码-->打开src/main/java中的dispatcher包中的SkillDispatch类，找到skillIntent方法进行编写代码实现您的技能，参考**代码开发示例**中的skillIntent方法代码-->打开src/main/java中的common包中的ProtocolUtils类，找到playTTs方法进行编写代码实现您的技能开始播放Tts，参考**代码开发示例**中的playTTs方法代码）。

（5）测试您的技能是否成功（先对您之间引入的skill-kit和skill-protocol项目分别进行 右键项目-->Run As-->Maven install，然后对您的项目进行 右键项目-->Run As-->Maven install，最后对您的项目进行 右键项目-->Run As-->Maven build `注意Maven build后的弹框中Goals填写tomcat:run`，没有报错即可按照后期中的**测试**进行测试您的技能是否正确无误）。


**代码开发示例：**

您可以在下面展示的方法中更改自己的开发技能代码。

用户：打开我要喝咖啡

处理1：

（1）dispatcher包下面的skillDispatcher类下面的appStartIntent方法处理，代码如下：
```Java
@Override
public ResResponse appStartIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
        HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
        ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException {
    return ProtocolUtils.pickUp("您好，请问有什么可以帮您");
}
```
（2）通过调用ProtocolUtils类的pickUp方法，将您想说的话表达出来。

（3）pickUp里面调用skill-protocol依赖下ResponseBuilder下面的处理方法,代码如下：
```Java
public static final ResResponse pickUp(String content) throws RokidException{
    try {
        return ResponseBuilder.build().voicePlay(null, content).pickupOpen().sendChatCard(content).create();
    } catch (ProtocolException e) {
        throw new RokidException(HttpStatus.INTERNAL_SERVER_ERROR.value(), RokidException.ERROR_CODE_RESONSE,
                e.getCause());
    }
}
```
*方法解释*

voicePlay():音频播放内容；

sendCahtCard():手机端接收到的内容；

pickupOpen():打开六十秒拾音，期间可以不用说若琪,若三次说错，会进入chat聊天模式。

之后用户可以询问您正在语音交互中定义的用户语句

处理2：

（1）dispatcher包下面的skillDispatcher类下面的skillIntent方法处理，代码如下：
```Java
@Override
public ResResponse skillIntent(HttpServletRequest request, ReqRequest reqRequest, ReqBasicInfo basicInfo,
        HashMap<String, Object> attributes, HashMap<String, Slot> slots, ReqApplicationMedia mediaState,
        ReqApplicationVoice voiceState, ReqExtraMedia mediaExtra, ReqExtraVoice voiceExtra) throws RokidException {
    if (basicInfo.getActionName().equals("bestcoffeebar")) {//
        return ProtocolUtils.playTts("我看位于问溪路89号的米萨咖啡就很不错");
    } else if (basicInfo.getActionName().equals("nicedrink")) {//
        return ProtocolUtils.playTts("只要是咖啡都很好喝");
    }
    return ProtocolUtils.igonre();
}
```
（2）首先判断您想进入那一个意图，此时basicInfo.getActionName()得到的值对应您在语音交互下intent的值，然后分别作出对应的处理，调用ProtocolUtils包下的playTts方法，代码如下：
```Java
public static final ResResponse playTTs(String content) throws RokidException {
    try {
        return ResponseBuilder.build().voicePlay(null, content).sendChatCard(content).create();
    } catch (ProtocolException e) {
        throw new RokidException(HttpStatus.INTERNAL_SERVER_ERROR.value(), RokidException.ERROR_CODE_RESONSE,
                e.getCause());
    }
}
```
*方法解释*

afterFinish():方法：执行该方法后会退出该技能。


**此时一个简单的我要喝咖啡技能就大致完成了，此时您需要将项目重新clean和install：右击项目-->Run As-->Maven clean-->Maven install。**


**测试**:

**使用的测试工具**:postman

首先登录rokid开放平台-->点进您配置过的技能-->点击[集成测试]

在后端服务测试的输入语句中输入您想要测试的话 示例：

- 米萨咖啡哪种最好喝

点击[发送]获取`请求体`。

**点击发送前请确定您的服务开启（右击项目-->Run As-->maven build）**

在下面的服务请求会生成一个固定格式的请求，（如果没有，请检查输入语句是否是您定义过的语句）拷贝服务请求-->在您下载好的postman里面Body中粘贴服务请求-->选择raw形式传输-->在上面选择您的传输方式，然后填写您在本地的服务(格式：http://localhost:8080/war包名称/speechlet)

点击`Send`,查看是否有您想要得到的结果输出。配置如下图：![](https://d26dzxoao6i3hh.cloudfront.net/items/0B2P2y452J3M0c1C2h3s/_L%24%60EXB2SLD2B%40%6041%5D%60%5D0%24E.png?v=a1f1a840)

**如果全部通过，您就可以点击发布了，此时，一个完整的技能以及测试就全部完成了！下面是相关依赖的介绍，您想更深入了解技能开发可以阅读开发须知。**


**开发须知：**

**（1）开发技能总体流程：** 语音进入设备-->通过ASR技术（语音转文本），转换为文本-->通过NLP技术（文本转语意）-->发出一个request请求，由HTTP里面的raw方式进行数据的传输-->进入到skill-kit模块。

**（2）skill-kit的原理：** skill-kit：由dispatcher分发器进行业务的分发，dispatcher.base里面实现了多个分发器的请求处理的方法，skillIntentCommonDispatcher里面定义了应用开始请求appStartIntent和应用退出请求appStopIntent，以及需要开发者自己定义的skillIntent，用户根据自己技能的需求在实现的skillIntent里面实现自己的技能.。

**（3）进入skill-kit入口：** 开发者可以通过设置URL后缀来选择进入”/healthcheck”或者”/speechlet”。开发者开发的技能的统一入在”/speechlet”，”/healthcheck”健康检查是用来监控自己的技能服务的稳定性，开发者可以实现healthcheckService来完善自己的健康检查服务。服务请求进入Filter（过滤器，用来记录请求服务名称和请求开始时间）。

**（4）进入skill-kit的拦截器：** 进入interceptor（拦截器）：分为控制拦截器和健康检查拦截器。Interceptor preHandle根据URI定义的服务类型判断是healthcheck还是skill请求，然后进行相应的数据处理防止出现空指针异常。Interceptor postHandle还定义了创建请求服务日志(serviceLogService)和语音请求日志服务(speechletLogService)方法。

**(5)进入skill-kit的控制器：** controller定义了healthcheckController和skillcontroller，Healthcontroller中会调用开发者自己实现的健康检查服务的check方法，skillcontroller作为语音请求业务入口，首先获得一个speechlet的分发器对basicInfo的ActionType判断之后，调用speechletDispatcher实现类的dispatchSpeechletCall方法，这里开发者可以选择自己实现SpeechletDispatcher或者继承AbstractSpeechletDispatcher(实现了SpeechletDispatcher的抽象类)，将自己的业务交由上层接管。AbstractSpeechletDispatcher：获取不同事件的分发器。

**（6）进入skill-kit的resolver：** resolver通过resolveException进行安全检查，分为两部分检查，一部分是服务器的检查（比如服务器端口号的检查），另一部分为HTTP的状态码检查（是否返回值为200），出现错误之后serverExceptionDispatcher进行语音服务处理异常！正确之后由skill-protocol工具类中的ResponseUtils类中的responseToString方法响应转为字符串，之后写入响应、开始日志的记录和开始语音请求记录。

**（7）skill-kit其他模块介绍：**


---1）bean包下有DefServiceLog类（系统请求日志信息），DefSpeechletLog类（语音业务日志）。

---2）Common包下都是一些共有的工具类。

---3）Constants包下是一些定义类，主要定义一些常用的信息。

---4）Converter包下是编码转换器。

---5）Exception包是异常包，有通用异常定义和语音业务异常。

---6）service开发者可自定的服务（健康检查服务，记录日志服务，安全检查服务）。
### 6.集成测试
技能开发完成之后，需要进行测试再进行发布，此处介绍测试的流程。
#### 6.1 设备绑定
在技能开发流程选项中，选中集成测试，点击添加自定义设备进行测试设备的绑定。每个设备由设备类型和设备ID进行唯一标识，在添加测试设备时，需要提供`设备TypeID`以及`设备ID`，如图所示添加一台设备。
![](http://ofrdtlim5.bkt.clouddn.com/20180116154522_yVP6mu_Jietu20180116-154500.jpeg)
#### 6.2 真机测试
我们可以使用真机进行功能的直观测试，打开若琪，使用语音直接进行交互，使用入口词唤醒技能，检查技能功能的正确性。
#### 6.3 通过日志调试
当真机测试出现功能问题时，需要通过查看日志来定位问题。此处需要使用的工具为`adb调试工具包`。首先需要进行adb工具包的安装和环境变量的配置，具体步骤参照[adb安装教程](https://jingyan.baidu.com/article/e6c8503c6b15a1e54f1a1818.html)。
adb工具安装成功后，将若琪开机，使用USB线连接若琪与电脑，在电脑命令窗口使用指令`adb devices`可查看连接的设备。接着在命令行输入`adb shell`与设备进行连接，然后命令行输入`logcat | grep 'asr'`进行日志的查看，通过检查日志，可以追踪设备运行的状态和运行的过程，从而定位问题。
