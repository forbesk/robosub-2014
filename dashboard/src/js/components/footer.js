define(['react', 'underscore'], function(React, _) {
    var Footer = React.createClass({
        render: function() {
            return React.DOM.footer({},
                React.DOM.a({}, "All rights reserved 2014, AUVA")
            );
        }
    });

    return Footer;
})
