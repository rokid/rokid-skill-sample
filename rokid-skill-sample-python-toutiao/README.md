## Skill Python Sample
### 基础环境
**操作系统：** CentOS 6.5+ <br>
**Python version：** 2.6+ <br>
**依赖：**
```bash
yum install -y python-devel openssl openssl-devel python-pip git libffi-devel
pip install flask requests pyOpenSSL
```
### 部署服务
##### 获取sample code
```bash
git clone https://github.com/Rokid/rokid-openvoice.git
cd rokid-openvoice/sample-code-python/
```
##### 获取SSL证书
详见：[通过Let’s Encrypt获取免费的SSL证书](https://developer-forum.rokid.com/t/lets-encrypt-ssl/175)
```bash
git clone https://github.com/Rokid/rokid-skill-tools.git
cd rokid-skill-tools
chmod +x generate-letsencrypt-certificate.sh
SKILL_DOMAIN=填写测试域名
./generate-letsencrypt-certificate.sh $SKILL_DOMAIN
mv $SKILL_DOMAIN.crt ../test_domain.crt
mv $SKILL_DOMAIN.key ../test_domain.key
```
##### 启动服务
```bash
cd .. && python sample-server.py
```
### 创建技能
详见：[Rokid Skills Start ](https://github.com/Rokid/docs/blob/master/1-GetStarted/Rokid%20Skills%20Kit.md)
##### 后端服务：
```
https://填写测试域名/
```
##### 意图定义：
```javascript
{
	"intents": [
		{
			"intent": "query_hot_words",
			"slots": []
		}
	]
}
```
##### 用户语句：
```
query_hot_words 今天热词是什么
```
### 测试技能
![Alt text](./skill-test.png)
