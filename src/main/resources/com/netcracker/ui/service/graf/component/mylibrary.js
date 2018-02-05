// Define the namespace
var mylibrary = mylibrary || {};

mylibrary.MyComponent = function (element) {
        element.innerHTML = "<div id='mynetwork'></div>";
        var self = this; // Can't use this inside the function
        var nodesId = null;//Хранит nodesId, по которой был сделан клик
        
        var nodes = null;
        var edges = null;
        var network = "";//просто как строка, только для того, чтобы не вылетало исключение, при задании события клика по ноде
        /*var svg = '<svg xmlns="http://www.w3.org/2000/svg" width="390" height="390">' +
            '<rect x="0" y="0" width="100%" height="100%" fill="#7890A7" stroke-width="20" stroke="#ffffff" ></rect>' +
            '<foreignObject x="15" y="10" width="100%" height="100%">' +
            '<div xmlns="http://www.w3.org/1999/xhtml" style="font-size:40px">' +
            ' <em>I</em> am' +
            '<span style="color:white; text-shadow:0 0 20px #000000;">' +
            ' HTML in SVG!</span>' +
            '</div>' +
            '</foreignObject>' +
            '</svg>';*/

        //var url = "data:image/svg+xml;charset=utf-8,"+ encodeURIComponent(svg);
        
        // Called when the Visualization API is loaded.
        this.draw = function (nodesBuf, nodesConnectionsBuf) {
            //SVG-вариант
            //nodes.push({id: 1, label: 'Get HTML', image: url, shape: 'circularImage'});
            //nodes.push({id: 2, label: 'Using SVG', image: url, shape: 'circularImage'});
            //Просто картинка
            // Create a data table with nodes.
            nodes = [];

            // Create a data table with links.
            edges = [];
            for (var i = 0; i < nodesBuf.length; i++) 
            {
                nodes.push({id: nodesBuf[i].newNodesId, label: nodesBuf[i].newNodesLabel, image: nodesBuf[i].newNodesimageUrl, shape: 'circularImage'});
            }
            for (var i = 0; i < nodesConnectionsBuf.length; i++) 
            {
                edges.push({from: nodesConnectionsBuf[i].idNodesConnectedFrom, to: nodesConnectionsBuf[i].idNodesConnectedTo, length: 100});
            }

            // create a network
            var container = document.getElementById('mynetwork');
            var data = {
                nodes: nodes,
                edges: edges
            };
            var options = {
                physics: {stabilization: false},
                edges: {smooth: false}
            };
            network = new vis.Network(container, data, options);
            element.style.width="100%";
            element.style.height="100%";
            //создал здесь, потом учто это событие нельзя на значить на простые типы, вроде int и string
            network.on("click", function (params) {
                nodesId = params["nodes"];
                if(nodesId > 0)
                {
                    self.click();
                }
            });
        };
        
        // Style it
        element.style.border = "thin solid red";
        element.style.display = "inline-block";

        // Getter and setter for the value property
        this.getValue = function () {
                return nodesId;
        };
        
        this.setValue = function () {
                return nodesId;
        };
};