var Backbone = require("backbone");

var Router = Backbone.Router.extend({
    routes: {
        'mission': "mission",
        'filters': "filter"
    },
    mission: function() {
        console.log("mission");
    },
    filter: function() {
        console.log("filter");
    }
});

module.exports = Router;
