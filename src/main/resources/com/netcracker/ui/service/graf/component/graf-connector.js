window.com_netcracker_ui_service_graf_component_Graf =
function() {
    // Create the component
    var mygraf =
        new mylibrary.MyGraf(this.getElement());

    // Handle changes from the server-side
    this.onStateChange = function() {
        //alert(this.getState().nodes[0].newNodesimageUrl);
        //mycomponent.draw(this.getState().newNodesimageUrl, this.getState().newNodesLabel, this.getState().idNodesConnectedFrom, this.getState().idNodesConnectedTo, this.getState().newNodesId);
        if(this.getState().event === "Initialize")
        {
            mygraf.draw(this.getState().nodes, this.getState().edges);
        }
        if(this.getState().event === "DeleteEdge")
        {
            mygraf.deleteNode(this.getState().edges[this.getState().edges.length-1].from, this.getState().edges[this.getState().edges.length-1].to);
        }
        this.getState().event = "";
    };

    // Pass user interaction to the server-side
    var self = this;
    mygraf.click = function() {
        self.onClick(mygraf.getCurrentData());
    };
};