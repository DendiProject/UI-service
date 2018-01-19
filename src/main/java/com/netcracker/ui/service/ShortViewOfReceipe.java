/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;


import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import java.io.File;
import org.vaadin.visjs.networkDiagram.Edge;
import org.vaadin.visjs.networkDiagram.NetworkDiagram;
import org.vaadin.visjs.networkDiagram.Node;
import org.vaadin.visjs.networkDiagram.options.Options;
import org.vaadin.visjs.networkDiagram.Node.Shape;
import org.vaadin.visjs.networkDiagram.api.JsonGenerator;


/**
 *
 * @author eliza
 */
import org.vaadin.visjs.networkDiagram.util.Fixed;
public class ShortViewOfReceipe extends HorizontalLayout {

    NetworkDiagram networkDiagram;
    Options options;

    public ShortViewOfReceipe() {

        options = new Options();
        networkDiagram = new NetworkDiagram(options);
        networkDiagram.setSizeFull();
        addComponent(networkDiagram);
        String path=VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        
        Image step1=new Image(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/step1.png");
        //step1.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/step1.png")));

        //crete nodes
        Node node1 = new Node(1,"box1",  "https://www.gastronom.ru/binfiles/images/20170418/bebd772a.jpg");
        Node node2 = new Node(2,"box2", " C:\\Users\\eliza\\project\\ui-service\\src\\main\\webapp\\WEB-INF\\images\\step2.png");
        Node node3 = new Node(3,"box3", path +  "/WEB-INF/images/step3.png");
        Node node4 = new Node(4,"box4",Node.Shape.box, path + "/WEB-INF/images/step4.png" );
        Node node5 = new Node(5, "box5",Node.Shape.box, path +   "/WEB-INF/images/step5.png");
        Node node6 = new Node(6, "box6",Node.Shape.box, path+   "/WEB-INF/images/step6.png");
        Node node7 = new Node(7, "box7",Node.Shape.box, path +   "/WEB-INF/images/step7.png");
        Node node8 = new Node(8,"box8",Node.Shape.box, path +  "/WEB-INF/images/step8.png");
        Node node9 = new Node(9, "box9",Node.Shape.box, path + "/WEB-INF/images/step9.png");
        Node node10 = new Node(10, "box10",Node.Shape.box, path+   "/WEB-INF/images/step10.png");
        Node node11 = new Node(11,"box11",Node.Shape.box, path + "/WEB-INF/images/step11.png");
        Node node12 = new Node(12,"box12",Node.Shape.box, path + "/WEB-INF/images/step12.png");
        
        Fixed f = new Fixed();
        f.setX(true);
        f.setY(true);
        node1.setFixed(f);
        node2.setFixed(f);
        node3.setFixed(f);
        node4.setFixed(f);
        node5.setFixed(f);
        node6.setFixed(f);
        node7.setFixed(f);
        node8.setFixed(f);
        node9.setFixed(f);
        node10.setFixed(f);
        node11.setFixed(f);
        node12.setFixed(f);
        
        node1.setX(-400);
        node1.setY(0);
        
        node2.setX(-400);
        node2.setY(100);
        
        node3.setX(-400);
        node3.setY(200);
        
        node4.setX(-400);
        node4.setY(300);
        
        node5.setX(-400);
        node5.setY(400);
        
        node6.setX(-400);
        node6.setY(500);
        
        node7.setX(-300);
        node7.setY(500);
        
        node8.setX(-300);
        node8.setY(400);
        
        node9.setX(-300);
        node9.setY(300);
        
        node10.setX(-300);
        node10.setY(200);
        
        node11.setX(-300);
        node11.setY(100);
        
        node12.setX(-300);
        node12.setY(0);    

        
        //create edges
        Edge edge1 = new Edge(node1.getId(), node2.getId());
        Edge edge2 = new Edge(node2.getId(), node3.getId());
        Edge edge3 = new Edge(node3.getId(), node4.getId());
        Edge edge4 = new Edge(node4.getId(), node5.getId());
        Edge edge5 = new Edge(node5.getId(), node6.getId());
        Edge edge6 = new Edge(node6.getId(), node7.getId());
        Edge edge7 = new Edge(node7.getId(), node8.getId());
        Edge edge8 = new Edge(node8.getId(), node9.getId());
        Edge edge9 = new Edge(node9.getId(), node10.getId());
        Edge edge10 = new Edge(node10.getId(), node11.getId());
        Edge edge11 = new Edge(node11.getId(), node12.getId());

        networkDiagram.stabilize();
        NetworkDiagram.ZoomListener zoomListener;
       
       
        //networkDiagram.setEnabled(false);
        //networkDiagram.removeZoomListener();
        //networkDiagram.addNode(node1);
        //networkDiagram
        networkDiagram.addNode(node1,node2, node3, node4, node5, node6, node7, node8, node9, node10, node11, node12);
        networkDiagram.addEdge(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9, edge10, edge11);
    }
}
