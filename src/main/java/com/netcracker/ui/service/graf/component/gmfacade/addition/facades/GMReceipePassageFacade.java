/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component.gmfacade.addition.facades;

import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.graf.component.gmfacade.workers.ReceipePassageWorker;
import com.netcracker.ui.service.passageReceipe.storages.InviteInformation;
import com.netcracker.ui.service.passageReceipe.storages.UserStep;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Artem
 */
public class GMReceipePassageFacade {
    private String connectionUrl;

    public GMReceipePassageFacade(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        //http://localhost:8083/
    }
    
    public void makeReceipe(String sessionId, String receipeId, String userId,
            List<String> userIds){
        try
        {
            ReceipePassageWorker receipePassageWorker = 
                    new ReceipePassageWorker(connectionUrl);
            receipePassageWorker.makeReceipe(sessionId, receipeId, userId, 
                    userIds);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
    }
    
    public List<InviteInformation> userStart(String userId){
        try
        {
            ReceipePassageWorker receipePassageWorker = 
                    new ReceipePassageWorker(connectionUrl);
            return receipePassageWorker.userStart(userId);
        }
        catch(Exception exception)
        {
            if(exception.getMessage().equals("404 Not Found")){
                List<InviteInformation> done = new ArrayList<InviteInformation>();
                InviteInformation inviteInformation = new InviteInformation();
                inviteInformation.setInviterId("-1");
                done.add(inviteInformation);
                return done;
            }
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public UserStep getNextStep (String sessionId, String userId, 
            String perviousNodeId){
        try
        {
            ReceipePassageWorker receipePassageWorker = 
                    new ReceipePassageWorker(connectionUrl);
            return receipePassageWorker.getNextStep(sessionId, userId, 
                    perviousNodeId);
        }
        catch(Exception exception)
        {
            if(exception.getMessage().equals("404 Not Found")){
                UserStep done = new UserStep();
                done.setIs404(true);
                return done;
            }
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public UserStep getNextStep (String sessionId, String userId){
        try
        {
            ReceipePassageWorker receipePassageWorker = 
                    new ReceipePassageWorker(connectionUrl);
            return receipePassageWorker.getNextStep(sessionId, userId);
        }
        catch(Exception exception)
        {
            if(exception.getMessage().equals("404 Not Found")){
                UserStep done = new UserStep();
                done.setIs404(true);
                return done;
            }
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public UserStep getNotCompletedStep (String sessionId, String userId){
        try
        {
            ReceipePassageWorker receipePassageWorker = 
                    new ReceipePassageWorker(connectionUrl);
            return receipePassageWorker.getNotCompletedStep(sessionId, userId);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
    
    public void completeReceipe(String sessionId, String receipeId, 
            String userId){
        try
        {
            ReceipePassageWorker receipePassageWorker = 
                    new ReceipePassageWorker(connectionUrl);
            receipePassageWorker.completeReceipe(sessionId, receipeId, userId);
        }
        catch(Exception exception)
        {
            //ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
    }
    
    public  Map<String, Boolean> getPassingGraph (String sessionId){
         try
        {
            ReceipePassageWorker receipePassageWorker = 
                    new ReceipePassageWorker(connectionUrl);
            return receipePassageWorker.getPassingGraph(sessionId);
        }
        catch(Exception exception)
        {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
            return null;
        }
    }
}
