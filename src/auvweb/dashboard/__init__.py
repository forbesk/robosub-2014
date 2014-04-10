from flask import Blueprint, render_template

dashboard = Blueprint("dashboard", __name__,
        static_folder="static",
        template_folder="templates")

@dashboard.route("/")
def main():
    return render_template("index.html")