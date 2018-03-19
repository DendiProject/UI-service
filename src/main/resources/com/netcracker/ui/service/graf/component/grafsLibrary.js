// Define the namespace
var mylibrary = mylibrary || {};

mylibrary.MyGraf = function (element) {
    var nodes = null;
    var edges = null;
    var network = null;
    var self = this; // Can't use this inside the function
    var nodesId = null;//Хранит nodesId, по которой был сделан клик
    var event = null;//Хранит название события
    // randomly create some nodes and edges
    //var data = getScaleFreeNetwork(25);
    var seed = 2;


    this.draw = function (nodesBuf, nodesConnectionsBuf) {
        nodes = [];
        edges = [];
        for (var i = 0; i < nodesBuf.length; i++) 
        {
            nodes.push({id: nodesBuf[i].id, label: nodesBuf[i].label, image: nodesBuf[i].image, shape: 'circularImage'});
        }
        for (var i = 0; i < nodesConnectionsBuf.length; i++) 
        {
            edges.push({from: nodesConnectionsBuf[i].from, to: nodesConnectionsBuf[i].to, length: 100});
        }
        var data = {
            nodes: nodes,
            edges: edges
        };
        // create a network
        var container = document.getElementById('mynetwork');
        var options = {
          layout: {randomSeed:seed}, // just to make sure the layout is the same when the locale is changed
          manipulation: {
            addNode: function (data, callback) {
              // filling in the popup DOM elements
              document.getElementById('operation').innerHTML = "Add Node";
              document.getElementById('node-id').value = data.id;
              document.getElementById('node-label').value = data.label;
              document.getElementById('saveButton').onclick = saveData.bind(this, data, callback);
              document.getElementById('cancelButton').onclick = clearPopUp.bind();
              document.getElementById('network-popUp').style.display = 'block';
              document.getElementById('node-image').value = 'url';
              
            },
            editNode: function (data, callback) {
              // filling in the popup DOM elements
              document.getElementById('operation').innerHTML = "Edit Node";
              document.getElementById('node-id').value = data.id;
              document.getElementById('node-label').value = data.label;
              document.getElementById('saveButton').onclick = saveData.bind(this, data, callback);
              document.getElementById('cancelButton').onclick = cancelEdit.bind(this,callback);
              document.getElementById('network-popUp').style.display = 'block';
              document.getElementById('node-image').value = 'url';
            },
            addEdge: function (data, callback) {
              if (data.from == data.to) {
                var r = confirm("Do you want to connect the node to itself?");
                if (r == true) {
                  callback(data);
                }
              }
              else {
                callback(data);
              }
            }
          }
        };
        network = new vis.Network(container, data, options);
        //создал здесь, потом учто это событие нельзя на значить на простые типы, вроде int и string
        network.on("click", function (params) {
            nodesId = params["nodes"];
            if(nodesId > 0)
            {
                event = 'ClickOnNode';
                self.click();
            }
        });
    };
        
    // Style it
    //element.style.border = "thin solid red";
    //element.style.display = "inline-block";

    // Getter and setter for the value property
    this.onNodeClick = function () {
        return nodesId;
    };

    this.onUpdateData= function () {
        var data = {
            event: event,
            nodes: nodes
        };
        return data;
    };

    this.setValue = function () {
        return nodesId;
    };

        
        
    function clearPopUp() {
        document.getElementById('saveButton').onclick = null;
        document.getElementById('cancelButton').onclick = null;
        document.getElementById('network-popUp').style.display = 'none';
    }

    function cancelEdit(callback) {
        clearPopUp();
        callback(null);
    }

    function saveData(data,callback) {
        data.id = document.getElementById('node-id').value;
        data.label = document.getElementById('node-label').value;
        data.image = document.getElementById('node-image').value;
        data.shape = "circularImage";
        clearPopUp();
        callback(data);
        event = 'DataChange';
        self.dataChange();
    }
};