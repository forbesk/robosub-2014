from flask import Flask, redirect, url_for
from auvweb.api import api
from auvweb.dashboard import dashboard

app = Flask(__name__)
app.register_blueprint(api, url_prefix="/api/v1")
app.register_blueprint(dashboard, url_prefix="/dashboard")

@app.route("/")
def index():
    return redirect(url_for("dashboard.main"))
