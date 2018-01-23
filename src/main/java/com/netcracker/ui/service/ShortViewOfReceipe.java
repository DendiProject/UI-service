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
import com.vaadin.server.Resource;
import org.vaadin.visjs.networkDiagram.NetworkDiagram.ZoomListener;
import org.vaadin.visjs.networkDiagram.event.graph.NetworkEvent;
import org.vaadin.visjs.networkDiagram.options.Group;
import org.vaadin.visjs.networkDiagram.options.Groups;

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
        
        options.setAutoResize(true);
        //options.
        //String path=VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();        
        FileResource path = new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()));
        String path1=VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        Groups groups=new Groups();
        Group group= new Group();
        groups.addGroup("group", group);
        //crete nodes
        Node node1 = new Node(1,"Подготовьте ингридиенты",Shape.box, "group", "https://images.lady.mail.ru/2810/");
        Node node2 = new Node(2, "Мякоть отделить от кости и нарезать на кубики"+"\n"+" размером 2 на 2 см. Кости не выбрасывать.", " https://images.lady.mail.ru/2811/");
        Node node3 = new Node(3,"Морковь нарезать тонкой соломкой.","https://images.lady.mail.ru/2812/");
        Node node4 = new Node(4,"Лук нарезать полукольцами.", "https://images.lady.mail.ru/2813/" );
        Node node5 = new Node(5, "В казан налить примерно 1 см высотой" +"\n"+" растительного масла и нагреть", Node.Shape.box, "group_x",  "https://images.lady.mail.ru/2814/");
        Node node6 = new Node(6, "Переложить в кастрюлю лук и готовить "+"\n"+"до золотистого цвета (минуты 4).",  "https://images.lady.mail.ru/2815/");
        Node node7 = new Node(7, "Переложить к луку мякоть баранины, жарить минут 10,"+"\n"+" помешивая, до равномерной румяной корочки.",  "https://images.lady.mail.ru/2816/");
        Node node8 = new Node(8,"Добавить в кастрюлю морковь,"+"\n"+" пару минут дать ей просто полежать сверху,"+"\n"+" а затем перемешать. Повторять эту процедуру около 10 минут."+"\n"+"На этом же этапе к мясу можно всыпать кумин.",  "https://images.lady.mail.ru/2817/");
        Node node9 = new Node(9, "Залить мясо с овощами кипятком,"+"\n"+" чтобы жидкость на палец покрывала содержимое кастрюли. "+"\n"+"Бросить в кастрюлю зубчики чеснока."+"\n"+" Довести до кипения и убавить огонь."+"\n"+" Готовить около получаса, не закрывая крышкой. Посолить.",  "https://images.lady.mail.ru/2818/");
        Node node10 = new Node(10, "Промыть рис, добавить его к содержимому,"+"\n"+" варить до готовности.", "https://images.lady.mail.ru/2819/");
        Node node11 = new Node(11,"Приятного аппетита!", "https://images.lady.mail.ru/2820/");
        //Node node12 = new Node(12,"Оставить на тихом огне до готовности риса",Node.Shape.box, path+ "/WEB-INF/images/step11.png");
        
        
         Group group2= new Group();       
         
         group.setRadius(10);
         group.setFontFace("sans-serif");
         group.setFontSize(15);
         
        options.setGroups(groups);
        
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
       // node12.setFixed(f);
        
        node1.setX(-400);
        node1.setY(0);
        
        node2.setX(-400);
        node2.setY(200);
        
        node3.setX(-400);
        node3.setY(400);
        
        node4.setX(-100);
        node4.setY(400);
        
        node5.setX(-100);
        node5.setY(200);
        
        node6.setX(-100);
        node6.setY(0);
        
        node7.setX(200);
        node7.setY(0);
        
        node8.setX(200);
        node8.setY(200);
        
        node9.setX(200);
        node9.setY(400);
        
        node10.setX(500);
        node10.setY(400);
        
        node11.setX(500);
        node11.setY(200);
        
       // node12.setX(-100);
       // node12.setY(0);    

        
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
       // Edge edge11 = new Edge(node11.getId(), node12.getId());

        networkDiagram.stabilize();


        NetworkDiagram.ZoomListener zoomListener;
        
        setWidth("1000px");
        setHeight("800px");
       HorizontalLayout lay= new HorizontalLayout();
      
        
        networkDiagram.addNode(node1,node2, node3, node4, node5, node6, node7, node8, node9, node10, node11);
        networkDiagram.addEdge(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9, edge10);
    }
            
}
