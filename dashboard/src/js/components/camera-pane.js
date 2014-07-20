/** @jsx React.DOM */

var React = require('react');

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

module.exports = CameraPane;
