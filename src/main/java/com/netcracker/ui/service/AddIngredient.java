/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.io.File;

/**
 *
 * @author Tsits
 */

public class AddIngredient extends Add
{        
    Image img = new Image();
    ComboBox autocomplite = new ComboBox();
    TextField name = new TextField ("Name");
    TextField description = new TextField ("Description");
    ComboBox size = new ComboBox("Размерность");
    TextField count = new TextField ("Сколько");
    Button add1 = new Button("Добавить (что-то)");
    Button add = new Button("Добавить");
    Button cancel = new Button("Отмена");
    Button plus_autocomplite = new Button("+");

    public AddIngredient() 
    {         
        
        super();
        HorizontalLayout h1 = new HorizontalLayout();
        h1.addComponent(autocomplite);
        h1.addComponent(plus_autocomplite);
        VerticalLayout vl1 = new VerticalLayout();
        vl1.addComponent(new Label("Autocomplite"));
        vl1.addComponent(h1);
        vl1.addComponent(name);
        vl1.addComponent(description);
        vl1.addComponent(add1);
        vl1.addComponent(size);
        super.leftLayout.addComponent(vl1);

        
        img.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/1.png")));      
        img.setHeight("250px");
        super.rightLayout.addComponent(img);
        super.rightLayout.addComponent(count);
        HorizontalLayout h2 = new HorizontalLayout();
        h2.addComponent(add);
        h2.addComponent(cancel);
        super.rightLayout.addComponent(h2);
    }
    
    private void nameValidation()
    {
        //firstName.setIcon(FontAwesome.AMBULANCE);
        StringLengthValidator slv = new StringLengthValidator("The name must be 3-10 letters (was {0})",3,10);        
      //  firstName.setBuffered(true);
       // firstName.addValidator(slv);


    }
}       
