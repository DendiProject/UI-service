// Define the namespace
var mylibrary = mylibrary || {};

mylibrary.MyGraf = function (element) {
    var nodes = null;
    var edges = null;
    var receipeId = "";
    var userId = "";
    var network = null;
    var self = this; // Can't use this inside the function
    var seed = 2;
    var xClick = 0;//координаты клика по рабочему полю
    var yClick = 0;//координаты клика по рабочему полю
    var returnUnswer;//Содержит данные об обновлении стейта

    this.draw = function (newstate) {
        /*nodes = [];
        edges = [];
        for (var i = 0; i < nodesBuf.length; i++) 
        {
            nodes.push({id: nodesBuf[i].id, label: nodesBuf[i].label, image: nodesBuf[i].image, shape: 'circularImage'});
        }
        for (var i = 0; i < nodesConnectionsBuf.length; i++) 
        {
            edges.push({from: nodesConnectionsBuf[i].from, to: nodesConnectionsBuf[i].to, length: 100});
        }*/
        var state = JSON.parse(newstate);
        var data = {
            nodes: state.nodes,
            edges: state.edges
        };
        receipeId = state.receipeId;
        userId = state.userId;
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
                            network.disableEditMode();
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
                        network.disableEditMode();
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
                            network.disableEditMode();
                        }
                    }
                    else {
                        //Синхронизация стейта
                        returnUnswer.editableEdgesNewIdFrom = data.from;
                        returnUnswer.editableEdgesNewIdTo = data.to;
                        //Обновление состояния
                        self.click();
                        network.disableEditMode();
                    }
                }
            }
	};
        network = new vis.Network(container, data, options);
        //создал здесь, потому что это событие нельзя на значить на простые типы, вроде int и string
        network.on("click", function (params) {
            var nodesId = params["nodes"];
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

    // Getter and setter for the value property
    this.getCurrentData= function () {
        //Данные на стороне java парсятся автоматически
        return returnUnswer;
    };


    //Функции для работы с графом
    
    //Функция добавления ноды, через изменение стейта
    //оповещает java и, следуя своей логике, java вызовет метод добавления 
    //конкретной ноды на стороне js
    function addNodeSideJS(id, label, image, receipeId, userId, newNodesDescription) {
        //Синхронизация состояния
        returnUnswer ={
            newNodesId: id,
            newNodesLable: label,
            newNodesImage: image,
            newNodesX: xClick,
            newNodesY: yClick,
            userId: userId,
            receipeId: receipeId,
            newNodesDescription: newNodesDescription
        };
        //Обновление состояния
        self.click();
    }
    this.addNodeSideJAVA= function (id, label, image) {
        addNode(id, label, image);
    };
    function addNode(id, label, image) {
        //Создание в правильном формате новой ноды
        var newData =  {
            id: id,
            label: label,
            shape: "circularImage",
            image: image,
            x: xClick,
            y: yClick
        };
        network.body.data.nodes.getDataSet().add(newData);
    }
    
    
    //Функция добавления связи, вызывает соответствующий режим vis 
    //js, который, после считывания нужных данных, через изменение стейта
    //оповещает java и, следуя своей логике, java вызовет метод добавления 
    //конкретной связи на стороне js 
    function addEdgeSideJS() {
        network.addEdgeMode();
    }
    //Вызов функции добавления ноды со стороны Java
    this.addEdgeSideJAVA= function (from, to) {
        addEdge(from, to);
    };
    function addEdge(from, to) {
        var finalizedData ={
            from: from,
            to: to,
            arrows: "to"
        };
        network.body.data.edges.getDataSet().add(finalizedData)
    }
    
    
    //Функция редактирования связи,вызывает соответствующий режим vis 
    //js, который, после считывания нужных данных, через изменение стейта
    //оповещает java и, следуя своей логике, java вызовет метод редактирования 
    //конкретной связи на стороне js
    function editEdgeSideJS() {
        var idEdge = 0;
        if(network.getSelectedEdges().length > 0)
        {
            idEdge = network.getSelectedEdges()[0];
            //Синхронизация состояния
            returnUnswer ={
                editableEdgesOldIdFrom: network.getConnectedNodes(idEdge)[0],
                editableEdgesOldIdTo: network.getConnectedNodes(idEdge)[1],
                editableEdgesNewIdFrom: 0,
                editableEdgesNewIdTo: 0,
                idEditableEdge: idEdge
            };
            
            network.editEdgeMode();
        }
    }
    this.editEdgeSideJAVA= function (from, to, id) {
        editEdge(from, to, id);
    };
    function editEdge(from, to, id) {
        var finalizedData = {
            from: from,
            id: id,
            to: to
        };
        network.body.data.edges.getDataSet().update(finalizedData);
        network.disableEditMode();
    }
    
    
    //Функция редактирования связи,вызывает соответствующий режим vis 
    //js, который, после считывания нужных данных, через изменение стейта
    //оповещает java и, следуя своей логике, java вызовет метод редактирования 
    //конкретной связи на стороне js
    function deleteEdgeSideJS(from, to) {
        var firstNode = network.getConnectedEdges(from);
        var secondNode = network.getConnectedEdges(to);
        for(var i=0; i<firstNode.length; i++){
            for(var j=0; j<secondNode.length; j++){
                if(firstNode[i] === secondNode[j]){
                    //Синхронизация состояния
                    returnUnswer ={
                        deleteEdgeFrom: from,
                        deleteEdgeTo: to,
                        deleteIdEdge: firstNode[i]
                    };

                    //Обновление состояния
                    self.click();
                    return;
                }
            }
        }
    }
    this.deleteEdgeSideJAVA= function (id) {
        deleteEdge(id);
    };
    function deleteEdge(id) {
        network.body.data.edges.getDataSet().remove(id,null);
    }
    
    
    //Функция редактирования связи,вызывает соответствующий режим vis 
    //js, который, после считывания нужных данных, через изменение стейта
    //оповещает java и, следуя своей логике, java вызовет метод редактирования 
    //конкретной связи на стороне js
    function deleteNodeSideJS(id) {
        //Синхронизация состояния
        returnUnswer ={
            deleteNodesId: id
        };
        //Обновление состояния
        self.click();
    }
    this.deleteNodeSideJAVA= function (id) {
        deleteNode(id);
    };
    function deleteNode(id) {
        network.body.data.nodes.getDataSet().remove(id,null);
    }
    
    
    //Функция удаления выделенного элемента
    function deleteSelectedElement(){
        //Вначале проверка на выделение ноды, она выделяется вместе со связью
        if(network.getSelectedNodes().length > 0)
        {
            var id = network.getSelectedNodes()[0];
            deleteNodeSideJS(id);
            return;
        }
        //Если пользователь хочет удалить связь, то он кликает именно на ней и нода
        //в таком случае не выделится
        if(network.getSelectedEdges().length > 0)
        {
            var id = network.getSelectedEdges()[0];
            deleteEdgeSideJS(network.getConnectedNodes(id)[0], network.getConnectedNodes(id)[1]);
        }
    }
    
    
    
    
    //Кнопки для вызовов функций графа со стороны js
    var addNodeBtn = document.getElementById("networkAddNode");
    addNodeBtn.onclick = function(event){
        var a = receipeId;
        var b = userId;
        addNodeSideJS(767686,"newNode","https://png.icons8.com/edit-property/nolan/64", receipeId, userId,"description");
    };
     
    var addEdgeBtn = document.getElementById("networkAddEdge");
    addEdgeBtn.onclick = function(event){
        addEdgeSideJS();
    };
    
    var editEdgeBtn = document.getElementById("networkEditEdge");
    editEdgeBtn.onclick = function(event) 
    {
        editEdgeSideJS();
    };

    var deleteElementBtn = document.getElementById("networkDeleteElement");
    deleteElementBtn.onclick = function(event) 
    {
        deleteSelectedElement();
    };
};