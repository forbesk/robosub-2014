/** @jsx React.DOM */

var React = require('react');
var Header = require('./header');
var Footer = require('./footer');
var Telemetry = require('./telemetry');

var Application = React.createClass({
    render: function() {
        return (
            <div id="container">
                <Header status={"running"} />
                <div className="wrapper">
                    <Telemetry />
                </div>
                <Footer />
            </div>
        );
    }
});

module.exports = Application;
