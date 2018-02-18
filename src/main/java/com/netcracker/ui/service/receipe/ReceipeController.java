/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.NodesConnection;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Notification;
import java.io.IOException;
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
        ReceipeDataStore data= new ReceipeDataStore();
        JSONObject receipeJSON = data.getResult(receipeId,"http://localhost:8082/v1/Receipe");
        ObjectMapper mapper = new ObjectMapper();
        Receipe receipe = new Receipe();
        try {
            receipe = (Receipe) mapper.readValue(receipeJSON.toString(),Receipe.class);
            //System.out.println(mapper.writeValueAsString(receipe));
        }
        catch (IOException ex) {
            Notification.show("Все очень плохА((((");
        }
        return receipe;
    }
}








