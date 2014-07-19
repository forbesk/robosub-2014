define(['react', 'underscore', 'bootstrap'], function(React, _, Bootstrap) {
    var Application = React.createClass({
        render: function() {
            return React.DOM.div({}, "Hello world");
        }
    });

    return Application;
})
