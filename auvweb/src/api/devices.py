from flask import jsonify
from auvweb.api import api

@api.route("/devices")
def index():
    '''
    Enumerate all recognized devices.
    '''
    return jsonify(devices=[])
