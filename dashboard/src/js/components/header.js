define(['react', 'underscore'], function(React, _) {
    var HeaderNav = React.createClass({
        render: function() {
            return React.DOM.nav({},
                React.DOM.a({href: "."}, "Dashboard"),
                React.DOM.a({href: "mission"}, "Mission Planner"),
                React.DOM.a({href: "filters"}, "Filters"),
                React.DOM.a({ id: "status", className: this.props.status })
            );
        }
    });

    var Header = React.createClass({
        render: function() {
            return React.DOM.header({className: "clearfix"},
                React.DOM.a({id: "logo", href: "/"},
                    React.DOM.img({src: "images/logo.png", height: "50px"}),
                    React.DOM.span({id: "tagline"}, "Autonomous Underwater Vehicle - University of Arizona")
                ),
                HeaderNav({status: this.props.status})
            );
        }
    });

    return Header;
})
