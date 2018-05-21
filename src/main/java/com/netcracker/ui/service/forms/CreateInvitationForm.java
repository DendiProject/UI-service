/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.forms;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.content.handler.CookieHandler;
import com.netcracker.ui.service.content.handler.JWTHandler;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.forms.listeners.CreateInvitationListener;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;
import com.netcracker.ui.service.graf.component.ipsFacade.IpsFacade;
import com.netcracker.ui.service.graf.component.ipsFacade.stores.UserInfo;
import com.netcracker.ui.service.passageReceipe.storages.InviteInformation;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;

/**
 *
 * @author Artem
 */
public class CreateInvitationForm  extends Window {
    
    private List<String> users = new ArrayList<String>();
    private List<UserInfo> allInfoOfAllUsers = new ArrayList<UserInfo>();
    private String sessionId;
    private String userid;
    
    public CreateInvitationForm(CreateInvitationListener listener, String receipeId) 
    {         
        try{
            BeansFactory<IpsFacade> bf = BeansFactory.getInstance();
            IpsFacade ips = bf.getBean(IpsFacade.class);
            List<UserInfo> newAllInfoOfAllUsers = ips.getAllUsers();
            if(newAllInfoOfAllUsers != null){
                allInfoOfAllUsers = newAllInfoOfAllUsers;
            }
            
            CookieHandler ch2 = new CookieHandler();
            JWTHandler jwth2 = new JWTHandler();
            Cookie userCookie2 = ch2.getCookieByName("userInfo");
            userid = jwth2.readUserId(userCookie2.getValue(), "test");
        }
        catch(Exception exception){
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
        
        List<String> displayNames = new ArrayList<String>();
        for(int i=0; i<allInfoOfAllUsers.size();i++){
            if(allInfoOfAllUsers.get(i).getDisplayname() != null){
                displayNames.add(allInfoOfAllUsers.get(i).getDisplayname());
                continue;
            }
            if(allInfoOfAllUsers.get(i).getName() != null & allInfoOfAllUsers.get(i).getLastname() != null){
                displayNames.add(allInfoOfAllUsers.get(i).getLastname()+" "+allInfoOfAllUsers.get(i).getName());
                continue;
            }
            displayNames.add(allInfoOfAllUsers.get(i).getId());
        }
        ComboBox displayNamesCB = new ComboBox();
        displayNamesCB.setHeight("100%");
        displayNamesCB.setWidth("100%");
        displayNamesCB.setItems(displayNames);
        
        ResponsiveLayout mainLayout = new ResponsiveLayout();
        CustomLayout mainCustomLayout = new CustomLayout("CreateInvitationView");
        mainLayout.setHeight("100%");
        mainCustomLayout.setHeight("100%");
        mainLayout.addComponent(mainCustomLayout);
        
        
        mainCustomLayout.addComponent(displayNamesCB, "CreateInvitationCBPlace");
        mainCustomLayout.addComponent(new Label("Список участников"), "CreateInvitationLable");
        mainCustomLayout.addComponent(new Label("Создание команды"), "CreateInvitationLabel");
        Button sendNotifies = new Button("Начать");
        sendNotifies.addClickListener((event) -> {
            try{            
                int sessionLength = getSession().getAttribute(
                    "com.vaadin.spring.internal.UIScopeImpl$UIStore").
                    toString().split(",")[1].split("=")[1].length();
                    sessionId = getSession().getAttribute(
                    "com.vaadin.spring.internal.UIScopeImpl$UIStore").
                    toString().split(",")[1].split("=")[1].substring(0, 
                            sessionLength-1);
                //ДОБАВИТЬ ВМЕСТО ЦИКЛА СЮДА ЧТЕНИЕ ИЗ ТАБЛИЦЫ В МАССИВ USERS
                for(int i=0;i<allInfoOfAllUsers.size();i++){
                    users.add(allInfoOfAllUsers.get(i).getId());
                }
                
                //Создаем рецепт и высылаем приглашения
                BeansFactory<GMFacade> bf2 = BeansFactory.getInstance();
                GMFacade gmFacade = bf2.getBean(GMFacade.class);
                gmFacade.getGmReceipePassageFacade().makeReceipe(sessionId, 
                        receipeId, userid, users);
                
                //Проверка, что прохождение создалось
                List<InviteInformation> invaitsInitUser = gmFacade.
                        getGmReceipePassageFacade().userStart(userid);
                for(int i=0; i<invaitsInitUser.size(); i++){
                    if(invaitsInitUser.get(i).getInviterId().equals(userid)){
                        listener.onCreate();
                        
                        this.close();//Если совпало, то проверка выполнилась успешно
                        return;
                    }
                }
                new Notification("This is a error",
                                    "Неудачная попытка отправить уведомления",
                                    Notification.Type.ERROR_MESSAGE, true)
                                    .show(Page.getCurrent());
            }
            catch(Exception exception){
                ExceptionHandler.getInstance().runExceptionhandling(exception);
            }
        });
        mainCustomLayout.addComponent(sendNotifies, "CreateInvitationGoBtn");
        
        Button addUser = new Button("Добавить участника");
        sendNotifies.addClickListener((event) -> {
            //ДОБАВИТЬ СЮДА ЗАПОЛНЕНИЕ МАССИВА users
        });
        mainCustomLayout.addComponent(addUser, "CreateInvitationAddNameBtn");
        
        Label addUsersLabel = new Label("Список участников");
        addUsersLabel.setHeight("100%");
        //addUsersLabel.setWidth("100%");
        mainCustomLayout.addComponent(addUsersLabel, "CreateInvitationLable");
                
        setContent(mainLayout);
        setPosition(20, 150);
        setResizable(false);
        setModal(true);
    }
}