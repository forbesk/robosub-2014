/** @jsx React.DOM */

var React = require('react');
var Header = require('./header');
var Footer = require('./footer');
var TelemetryInfo = require('./telemetry-info');
var MissionInfo = require('./mission-info');
var CameraPane = require('./camera-pane');

var Application = React.createClass({
    render: function() {
        return (
            <div id="container">
                <Header status={"running"} />
                    <div className="row wrapper">
                        <CameraPane />
                        <TelemetryInfo />
                        <MissionInfo />
                    </div>
                <Footer />
            </div>
        );
    }
});

module.exports = Application;
