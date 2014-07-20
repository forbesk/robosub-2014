define(['react', 'underscore', 'bootstrap'], function(React, _, Bootstrap) {

    var Camera = React.createClass({
        render: function() {
            return React.DOM.div({className: "well col-md-4"}, this.props.name)
        }
    });

    var CameraPane = React.createClass({
        render: function() {
            return React.DOM.div({className: "col-md-4"},
                React.DOM.div({className: "row"},
                    Camera({name: "Camera 1"}),
                    Camera({name: "Camera 2"}),
                    Camera({name: "Camera 3"})
                )
            );
        }
    });

    var Telemetry = React.createClass({
        render: function() {
            return React.DOM.div({className: "row"},
                CameraPane(),
                TelemetryInfo(),
                MissionInfo()
            )
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
                return React.DOM.tr({key: key},
                    React.DOM.td({}, key),
                    React.DOM.td({}, value.toFixed(2))
                );
            });

            return React.DOM.div({className: "col-md-4"},
                React.DOM.table({className: "table"},
                    React.DOM.th({colSpan: 2}, "Telemetry Info"),
                    rows
                )
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

                return React.DOM.tr({className: value.className, key: timestamp},
                    React.DOM.td({}, date.toLocaleString()),
                    React.DOM.td({}, value.text)
                );
            });

            return React.DOM.div({className: "col-md-4"},
                React.DOM.table({className: "table"},
                    React.DOM.th({colSpan: 2}, "Mission Info"),
                    rows
                )
            );
        }
    });

    return Telemetry;
})
