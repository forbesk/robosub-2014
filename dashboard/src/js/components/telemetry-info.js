/** @jsx React.DOM */

var React =require('react');
var _ = require('lodash');

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
                    <th colSpan={2}>Telemetry Info</th>
                    {rows}
                </table>
            </div>
        );
    }
});

module.exports = TelemetryInfo;
