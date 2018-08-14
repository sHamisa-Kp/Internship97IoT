# FLASK_APP=hello.py flask run

from flask import Flask
import time

app = Flask(__name__)


@app.route('/')
def hello():
    return 'Hello, World!'

#
# while True:
#     print("I am HERE")
#     time.sleep(2)