define(['backbone'], function(Backbone, _) {
    var join Array.prototype.join;

    var Base = Backbone.Model.extend({
        urlRoot: "/api/v1/",
        url: function () {
            return this.urlRoot + "/" +  this.defaults.modelname ;
        };
    });

    return Base;
});
