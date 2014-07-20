/** @jsx React.DOM */

var React = require('react');
var Header = require('./header');

var Application = React.createClass({
    render: function() {
        return (
            <Header status={"running"} />
        );
    }
});

module.exports = Application;
