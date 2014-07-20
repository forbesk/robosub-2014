define(['react'], function(React) {
    return {
        componentDidMount: function() {
            this.model.then(function(data) {
                this.setState({ model: data });
            }.bind(this));

});
