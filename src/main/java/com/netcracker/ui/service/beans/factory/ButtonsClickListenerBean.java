/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.beans.factory;

import com.netcracker.ui.service.beans.factory.basic.objects.interfaces.Product;
import com.netcracker.ui.service.buttonsClickListener.component.ButtonsClickListener;
import com.netcracker.ui.service.buttonsClickListener.component.ClickListener;
import com.netcracker.ui.service.content.handler.ContentManagerController;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Artem
 */
public class ButtonsClickListenerBean implements Product<ButtonsClickListener>{
    
    private ButtonsClickListener content;
    
    public ButtonsClickListenerBean()
    {
        setContent();
    }
    
    @Override
    public void setContent() {
        ButtonsClickListener listener = new ButtonsClickListener();
        content = listener;
    }

    @Override
    public ButtonsClickListener getContent() {
     
        return new ButtonsClickListener();
    }
    
}