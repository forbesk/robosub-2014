/** @jsx React.DOM */

var React = require('react');

var HeaderNav = React.createClass({
    render: function() {
        return (
            <nav>
                <a href=".">Dashboard</a>
                <a href="mission">Mission Planner</a>
                <a href="filters">Filters</a>
                <a id="status" className={this.props.status} />
            </nav>
        );
    }
});

var Header = React.createClass({
    render: function() {
        return (
            <header className="clearfix">
                <a id="logo" href="/" >
                    <img src="images/logo.png" height="50px" />
                    <span id="tagline">Autonomous Underwater Vehicle - University of Arizona</span>
                </a>
                <HeaderNav status={this.props.status} />
            </header>
        );
    }
});

module.exports = Header;
