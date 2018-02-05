/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;


import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.HorizontalLayout;
import org.vaadin.visjs.networkDiagram.Edge;
import org.vaadin.visjs.networkDiagram.NetworkDiagram;
import org.vaadin.visjs.networkDiagram.Node;
import org.vaadin.visjs.networkDiagram.options.Options;
import org.vaadin.visjs.networkDiagram.Node.Shape;
import org.vaadin.visjs.networkDiagram.api.JsonGenerator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.vaadin.visjs.networkDiagram.options.Group;
import org.vaadin.visjs.networkDiagram.options.Groups;
import org.vaadin.visjs.networkDiagram.options.ShapeProperties;
import org.vaadin.visjs.networkDiagram.options.modules.Edges;
import org.vaadin.visjs.networkDiagram.options.modules.Nodes;
import org.vaadin.visjs.networkDiagram.util.Color;

/**
 *
 * @author eliza
 */
import org.vaadin.visjs.networkDiagram.util.Fixed;
import org.vaadin.visjs.networkDiagram.util.Icon;
public class ShortViewOfReceipe extends HorizontalLayout {

    List<Node> nodes= new ArrayList();
    List<Edge> edges= new ArrayList();
    NetworkDiagram networkDiagram;
    Options options;

    public ShortViewOfReceipe() {

        
        
        options = new Options();
        networkDiagram = new NetworkDiagram(options);
        networkDiagram.setSizeFull();
        addComponent(networkDiagram);
        
        options.setAutoResize(true);           
        
        String groupName="group";
        Groups groups=new Groups();
        Group group= new Group();
        groups.addGroup(groupName, group); 
                 
        Color color = new Color();
        color.setColor("#FFEFD5");
         group.setFontFace("sans-serif");
         group.setFontSize(15);
         group.setRadius(10); 
         group.setShape(Shape.box);
         group.setColor(color);
        options.setGroups(groups);        
        
        //crete nodes
        Node node1 = new Node(1," ",Shape.box, groupName , "https://images.lady.mail.ru/2810/");
        Node node2 = new Node(2, "Мякоть отделить от кости и нарезать на кубики"+"\n"+" размером 2 на 2 см. Кости не выбрасывать.", Shape.box,"group", " https://images.lady.mail.ru/2811/");
        Node node3 = new Node(3,"Морковь нарезать тонкой соломкой.",Shape.box, groupName, "https://images.lady.mail.ru/2812/");
        Node node4 = new Node(4,"Лук нарезать полукольцами.", "https://images.lady.mail.ru/2813/" );
        Node node5 = new Node(5, "В казан налить примерно 1 см высотой" +"\n"+" растительного масла и нагреть", Node.Shape.box, "group",  "https://images.lady.mail.ru/2814/");
        Node node6 = new Node(6, "Переложить в кастрюлю лук и готовить "+"\n"+"до золотистого цвета (минуты 4).",  "https://images.lady.mail.ru/2815/");
        Node node7 = new Node(7, "Переложить к луку мякоть баранины, жарить минут 10,"+"\n"+" помешивая, до равномерной румяной корочки.",  "https://images.lady.mail.ru/2816/");
        Node node8 = new Node(8,"Добавить в кастрюлю морковь,"+"\n"+" пару минут дать ей просто полежать сверху,"+"\n"+" а затем перемешать. Повторять эту процедуру около 10 минут."+"\n"+"На этом же этапе к мясу можно всыпать кумин.",  "https://images.lady.mail.ru/2817/");
        Node node9 = new Node(9, "Залить мясо с овощами кипятком,"+"\n"+" чтобы жидкость на палец покрывала содержимое кастрюли. "+"\n"+"Бросить в кастрюлю зубчики чеснока."+"\n"+" Довести до кипения и убавить огонь."+"\n"+" Готовить около получаса, не закрывая крышкой. Посолить.",  "https://images.lady.mail.ru/2818/");
        Node node10 = new Node(10, "Промыть рис, добавить его к содержимому,"+"\n"+" варить до готовности.", "https://images.lady.mail.ru/2819/");
        Node node11 = new Node(11,"Приятного аппетита!", "https://images.lady.mail.ru/2820/");
        
        ShapeProperties pr = new ShapeProperties ();
       node1.setTitle("Шаг1");
        //node1.setShapeProperties(pr.);
        //options.setNodes(node1, node2, node3);
        
        Nodes nodes1 = new Nodes();
        
        
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);
        nodes.add(node6);
        nodes.add(node7);
        nodes.add(node8);
        nodes.add(node9);
        nodes.add(node10);
        nodes.add(node11);        
        
        
        Fixed f = new Fixed();
        f.setX(true);
        f.setY(true);        
                Iterator iterNodes =nodes.iterator();
                
         while (iterNodes.hasNext())
                {
                    Node node = (Node) iterNodes.next();
                    node.setFixed(f);                   
                }  
         
         nodesLocation(nodes);
         

        
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
       
        edges.add(edge1);
        edges.add(edge2);
        edges.add(edge3);
        edges.add(edge4);
        edges.add(edge5);
        edges.add(edge6);
        edges.add(edge7);
        edges.add(edge8);
        edges.add(edge9);
        edges.add(edge10);
        
        Iterator iterEgdes =edges.iterator();
         while (iterEgdes.hasNext())
                {
                    Edge edge = (Edge) iterEgdes.next();
                    edge.setWidth(4); 
                    edge.setColor(color);
                }        


        
        setWidth("1000px");
        setHeight("800px");
       HorizontalLayout lay= new HorizontalLayout();
      
        networkDiagram.addNodes(nodes);
        networkDiagram.addEdges(edges);
        //networkDiagram.addNode(node1,node2, node3, node4, node5, node6, node7, node8, node9, node10, node11);
        //networkDiagram.addEdge(edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9, edge10);
    }
    
    public void nodesLocation(List<Node> nodes)
    {
        Iterator iterNodes2 =nodes.iterator();
        int X=-700;
        int Y=0;
        int nodesCount=0;
        while (iterNodes2.hasNext())         
         {                            
            if (nodesCount<3)
            {
                X+=300;
                for (int i=0; i<3; i++)
                {
                      if (iterNodes2.hasNext())  
                      {
                       Node node = (Node) iterNodes2.next();   
                       node.setX(X);
                       node.setY(Y);
                       Y+=200;
                       nodesCount++; 
                    }
                }
            }
            else   
            {   X+=300; 
                for (int i=0; i<3; i++)
                {
                    if (iterNodes2.hasNext())  
                     {
                      Y-=200;
                      Node node = (Node) iterNodes2.next();  
                      node.setX(X);
                      node.setY(Y);
                      nodesCount--;  
                     }
                } 
            }
        }     
    }
            
}
