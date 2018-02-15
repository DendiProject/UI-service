/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe;

import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.NodesConnection;
import com.vaadin.server.VaadinService;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;

/**
 * Используется для обработки полученных данных для short view of receipe
 * Контролирует получение данных для short view of receipe и их представлении в
 * нужном виде (адаптер).
 * @author Artem
 */
@RestController
public class ReceipeController {
    public static Receipe getRecipe(int receipeId){
        Receipe receipe = new Receipe();
        ReceipeDataStore data= new ReceipeDataStore();
        JSONObject receipeJSON = data.getResult(receipeId,"http://localhost:8082/v1/Receipe");
        //JSONObject receipesSteps = receipe.getJSONObject("Receipe").getString("receipe_name");
        receipe.receipesName = receipeJSON.getJSONObject("Receipe").getString("receipe_name");
        JSONArray receipesSteps = receipeJSON.getJSONObject("Receipe").getJSONArray("steps");
        ArrayList<Node> nodes = new ArrayList<>();
        ArrayList<NodesConnection> nodesConnections = new ArrayList<>();
        
        for(int i=0;i<receipesSteps.length();i++)
        {
            JSONObject step = receipesSteps.getJSONObject(i);
            int newNodesImageId = step.getInt("image_id");
            //String newNodesimageUrl = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/top1.png";
            
            
            //тут должен быть запрос доступа к контент менеджеру на картинку
            String newNodesimageUrl="";
            if(newNodesImageId == 1)
            {
                newNodesimageUrl = "https://ru4.anyfad.com/items/t1@dd86b0fb-d896-4da6-80b4-7fe5c9e3e17c/samye-milye-kotyata.jpg";
            }
            else
            {
                newNodesimageUrl = "https://negani.com/uploads/posts/2012-02/1330543487_husky_47.jpg";
            }
            
            
            
            
            
            
            int newNodesId = step.getInt("image_id");
            String newNodesLabel = step.getString("destription");
            Node node = new Node(newNodesimageUrl, newNodesId, newNodesLabel);
            nodes.add(node);
            if(i!=0)
            {
                NodesConnection nodesConnection = new NodesConnection((newNodesId-1), newNodesId);
                nodesConnections.add(nodesConnection);
            }
        }
        receipe.nodes = nodes;
        receipe.nodesConnections = nodesConnections;
        return receipe;
    }
}
