# FLASK_APP=hello.py flask run

from flask import Flask

app = Flask(__name__)


@app.route('/')
def hello():
    return 'Hello IoT'


app.run(host='0.0.0.0', port=5050, debug=False)
