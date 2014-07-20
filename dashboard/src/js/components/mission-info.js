/** @jsx React.DOM */

var React =require('react');
var _ = require('lodash');

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

module.exports = MissionInfo;
