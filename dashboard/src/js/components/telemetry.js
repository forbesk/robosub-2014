/** @jsx React.DOM */

var React =require('react');
var _ = require('lodash');

var Camera = React.createClass({
    render: function() {
        return (
            <div className="well col-md-4">
                {this.props.name}
            </div>
        );
    }
});

var CameraPane = React.createClass({
    render: function() {
        return (
            <div className="col-md-4">
                <div className="row">
                    <Camera name="Camera 1" />
                    <Camera name="Camera 2" />
                    <Camera name="Camera 3" />
                </div>
            </div>
        );
    }
});

var Telemetry = React.createClass({
    render: function() {
        return (
            <div className="row">
                <CameraPane />
                <TelemetryInfo />
                <MissionInfo />
            </div>
        );
    }
});

var TelemetryInfo = React.createClass({
    getInitialState: function() {
        return {
            "Depth": 0.00,
            "Heading": 0.00,
            "Battery Voltage": 0.00,
            "Battery Current": 0.00,
            "Position": 0.00,
            "Hydrophones": 0.00
        };
    },
    render: function() {
        var rows = _.map(this.state, function(value, key, index) {
            return (
                <tr key={key}>
                    <td>
                        {key}
                    </td>
                    <td>
                        {value.toFixed(2)}
                    </td>
                </tr>
            );
        });

        return (
            <div className="col-md-4">
                <table className="table">
                    <th colSpan={2}>"Telemetry Info"</th>
                    {rows}
                </table>
            </div>
        );
    }
});

var MissionInfo = React.createClass({
    getInitialState: function() {
        return {
            "1397790449557" : {text: "The agent started", className: "info"},
            "1397792449357" : {text: "The agent turned right"},
            "1397790449257" : {text: "The agent moved forward"},
            "1397790492513" : {text: "The agent won its first task!", className: "success"},
            "1397790449057" : {text: "The agent had an error", className: "danger"},
            "1397793449157" : {text: "The agent moved forward"},
            "1397791448557" : {text: "The agent is on to its next task!", className: "active"}
        };
    },
    render: function() {
        var rows = _.map(this.state, function(value, timestamp) {
            var date = new Date(parseInt(timestamp, 10));

            return (
                <tr className={value.className} key={timestamp}>
                    <td>
                        {date.toLocaleString()}
                    </td>
                    <td>
                        {value.text}
                    </td>
                </tr>
            );
        });

        return (
            <div className="col-md-4">
                <table className="table">
                 <th colSpan={2}>Mission Info</th>
                    {rows}
                </table>
            </div>
        );
    }
});

module.exports = Telemetry;
