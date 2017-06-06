# coding=utf-8
__author__ = 'Panda zhaolin@rokid.com'
__create_time__ = '17/4/30'

from flask import Flask, request
import requests
import sys
import json

reload(sys)
sys.setdefaultencoding('utf-8')

SSL_CONTEXT = ("test_domain.crt", "test_domain.key")
app = Flask(__name__)

@app.route('/', methods=["POST"])
def request_handle():
    print request.data
    request_data = json.loads(request.data)
    intent = request_data.get("request").get("content").get("intent")
    if intent == "query_hot_words":
        hot_words = query_hot_words()
        return json.dumps(tts_response_format(",".join(hot_words)), ensure_ascii=False)
    else:
        return json.dumps(tts_response_format("我暂时不知道怎么回答你"), ensure_ascii=False)


def query_hot_words():
    hot_words_url = "https://www.toutiao.com/hot_words/"
    return json.loads(requests.get(hot_words_url).text)


def tts_response_format(tts):
    return {
        "version": "2.0.0",
        "session": {
        },
        "response": {
            "action": {
                "version": "2.0.0",
                "type": "NORMAL",
                "shoudEndSession": True,
                "voice": {
                    "needEventCallback": True,
                    "behaviour": "APPEND",
                    "item": {
                        "tts": tts
                    }
                }
            }
        }
    }


if __name__ == '__main__':
    app.run(host='0.0.0.0', port='443', debug=False, ssl_context=SSL_CONTEXT)