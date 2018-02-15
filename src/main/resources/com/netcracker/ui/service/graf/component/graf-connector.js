window.com_netcracker_ui_service_graf_component_Graf =
function() {
    // Create the component
    var mycomponent =
        new mylibrary.MyComponent(this.getElement());

    // Handle changes from the server-side
    this.onStateChange = function() {
        //alert(this.getState().nodes[0].newNodesimageUrl);
        //mycomponent.draw(this.getState().newNodesimageUrl, this.getState().newNodesLabel, this.getState().idNodesConnectedFrom, this.getState().idNodesConnectedTo, this.getState().newNodesId);
        mycomponent.draw(this.getState().nodes, this.getState().nodesConnections);
    };

    // Pass user interaction to the server-side
    var self = this;
    mycomponent.click = function() {
        self.onClick(mycomponent.getValue());
    };
};