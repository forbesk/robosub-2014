require.config({
    baseUrl: static_root + "js",
    paths: {
        'react': 'vendor/react/react',
        'underscore': 'vendor/underscore/underscore',
        'backbone': 'vendor/backbone/backbone',
        'jquery': 'vendor/jquery/dist/jquery.min',
        'bootstrap': 'vendor/bootstrap/dist/js/bootstrap.min'
    },
    shim: {
        backbone: {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        underscore: {
            exports: '_'
        },
        bootstrap: {
            deps: ['jquery']
        }
    }
});

require(['jquery', 'react', 'backbone', 'components/application'],
        function($, React, Bootstrap, Application) {

    $(document).ready(function() {
        var app = Application();

        Backbone.history.start({
            pushState: true,
            root: url_root
        });

        React.renderComponent(app, document.body);
    });
});
