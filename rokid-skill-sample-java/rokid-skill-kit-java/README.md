#rokid-skill-kit-java使用说明
##简述
Rokid-Skill-Kit-Java是以及基于Spring Mvc的Rokid skill开发框架，该框架包含如下两部分

##拓展SpingMvc配置文件
* 开发者可以在自己工程的resources目录下添加config目录，然后再该目录中添加.properties，则Skill-Kit会自动扫描并且加载.properties文件
* 开发者可以在自己工程的resources目录下添加skillbean目录，然后再该目录下添加spingmvc配置相关的拓展信息.xml文件，则Skill-kit会自动扫描并且加载.xml文件，该操作可以方便开发者拓展db配置以及redis等配置
