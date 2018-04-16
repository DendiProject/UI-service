// Define the namespace
var mylibrary = mylibrary || {};

mylibrary.MyGraf = function (element) {
    var nodes = null;
    var edges = null;
    var network = null;
    var self = this; // Can't use this inside the function
    // randomly create some nodes and edges
    //var data = getScaleFreeNetwork(25);
    var seed = 2;
    var xClick = 0;//координаты клика по рабочему полю
    var yClick = 0;//координаты клика по рабочему полю
    var returnUnswer;//Содержит данные об обновлении стейта

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
                enabled: false,
                addEdge: function (data, callback) {
                    console.log('add edge', data);
                    if (data.from == data.to) {
                        var r = confirm("Do you want to connect the node to itself?");
                        if (r === true) {
                            //Синхронизация стейта
                            returnUnswer ={
                                newEdgesFrom: data.from,
                                newEdgesTo: data.to,
                            };
                            //Обновление состояния
                            self.click();
                            
                            callback(data);
                        }
                    }
                    else {
                        //Синхронизация стейта
                        returnUnswer ={
                            newEdgesFrom: data.from,
                            newEdgesTo: data.to,
                        };
                        //Обновление состояния
                        self.click();
                        
                        callback(data);
                    }
                },
                editEdge: function (data, callback) {
                    console.log('add edge', data);
                    if (data.from == data.to) {
                        var r = confirm("Do you want to connect the node to itself?");
                        if (r === true) {
                            //Синхронизация стейта
                            returnUnswer.editableEdgesNewIdFrom = data.from;
                            returnUnswer.editableEdgesNewIdTo = data.to;
                            //Обновление состояния
                            self.click();
                            
                            callback(data);
                        }
                    }
                    else {
                        //Синхронизация стейта
                        returnUnswer.editableEdgesNewIdFrom = data.from;
                        returnUnswer.editableEdgesNewIdTo = data.to;
                        //Обновление состояния
                        self.click();

                        callback(data);
                    }
                }
            }
	};
        network = new vis.Network(container, data, options);
        //создал здесь, потому что это событие нельзя на значить на простые типы, вроде int и string
        network.on("click", function (params) {
            var nodesId = params["nodes"];
            //var edgesId = params["edges"];//id связи
            xClick = params.pointer.canvas.x;//координаты клика по рабочему полю
            yClick = params.pointer.canvas.y;//координаты клика по рабочему полю
            if(nodesId > 0)
            {
                //Синхронизация состояния
                returnUnswer ={
                    nodesIdClick: nodesId[0]
                };
                //Обновление состояния
                self.click();
            }
        });
    };
    
    
    
    /*var button =document.getElementById("mynetwork"); 
        button.onclick = function(event) 
        {
            var target = event.target; // где был клик?
            var action = target.getAttribute('class');
            //Кнопка, если последние 3 символа - Btn
            //Для начала проверяем длинну, чтобы не вылетало исключение
            if(action !== null && action.length>4)//название хотя бы 1 символ, 3 символа на Btn
            {
                //В конце каждого id кнопки должны стоять символы Btn
                var chekBtnSymbols = action.substr((action.length-3), 3);
                if(chekBtnSymbols === 'Btn')//Если true, то вызываем событие
                {     
                    //Синхронизация состояния
                    returnUnswer ={
                        nodesIdClick: action
                    };
                    //Обновление состояния
                    self.click();
                }
            }
        };*/
    
    
    // Style it
    //element.style.border = "thin solid red";
    //element.style.display = "inline-block";

    // Getter and setter for the value property
    this.getCurrentData= function () {
        //Данные на стороне java парсятся автоматически
        return returnUnswer;
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

    /*function saveData(data,callback) {
        data.id = document.getElementById('node-id').value;
        data.label = document.getElementById('node-label').value;
        data.image = document.getElementById('node-image').value;
        data.shape = "circularImage";
        clearPopUp();
        callback(data);
        event = 'DataChange';
        self.click();
    }*/
    
    var addNodeBtn = document.getElementById("networkAddNode");
    addNodeBtn.onclick = function(event) 
    {
        //Создание в правильном формате новой ноды
        var newData =  {
            id: 767686,
            label: "newNode",
            shape: "circularImage",
            image: "https://png.icons8.com/edit-property/nolan/64",
            x: xClick,
            y: yClick
        };
        network.body.data.nodes.getDataSet().add(newData);
        
        //Синхронизация состояния
        returnUnswer ={
            newNodesId: newData.id,
            newNodesLable: newData.label,
            newNodesImage: newData.image,
            newNodesX: newData.x,
            newNodesY: newData.y
        };
        //Обновление состояния
        self.click();
    };
    var addEdgeBtn = document.getElementById("networkAddEdge");
    addEdgeBtn.onclick = function(event){
        network.addEdgeMode();
    };
    
    var editEdgeBtn = document.getElementById("networkEditEdge");
    editEdgeBtn.onclick = function(event) 
    {
        var idEdge = 0;
        if(network.getSelectedEdges().length > 0)
        {
            idEdge = network.getSelectedEdges()[0];
            //Синхронизация состояния
            returnUnswer ={
                editableEdgesOldIdFrom: network.getConnectedNodes(idEdge)[0],
                editableEdgesOldIdTo: network.getConnectedNodes(idEdge)[1],
                editableEdgesNewIdFrom: 0,
                editableEdgesNewIdTo: 0
            };
            
            network.editEdgeMode();
        }
    };

    var deleteElementBtn = document.getElementById("networkDeleteElement");
    deleteElementBtn.onclick = function(event) 
    {
        //Вначале проверка на выделение ноды, она выделяется вместе со связью
        if(network.getSelectedNodes().length > 0)
        {
            var id = network.getSelectedNodes()[0];
            network.body.data.nodes.getDataSet().remove(network.getSelectedNodes()[0],null);

            //Синхронизация состояния
            returnUnswer ={
                deleteNodesId: id
            };
            //Обновление состояния
            self.click();
        }
        //Если пользователь хочет удалить связь, то он кликает именно на ней и нода
        //в таком случае не выделится
        if(network.getSelectedEdges().length > 0)
        {
            var id = network.getSelectedEdges()[0];
            //Синхронизация состояния
            returnUnswer ={
                deleteEdgeFrom: network.getConnectedNodes(id)[0],
                deleteEdgeTo: network.getConnectedNodes(id)[1]
            };
            
            network.body.data.edges.getDataSet().remove(network.getSelectedEdges()[0],null);

            //Обновление состояния
            self.click();
        }
    };
};