window.com_netcracker_ui_service_graf_component_Graf =
function() {
    // Create the component
    var mycomponent =
        new mylibrary.MyComponent(this.getElement());

    // Handle changes from the server-side
    this.onStateChange = function() {
        mycomponent.draw(this.getState().newNodesimageUrl, this.getState().newNodesLabel, this.getState().idNodesConnectedFrom, this.getState().idNodesConnectedTo, this.getState().newNodesId);
    };

    // Pass user interaction to the server-side
    var self = this;
    mycomponent.click = function() {
        self.onClick(mycomponent.getValue());
    };
};