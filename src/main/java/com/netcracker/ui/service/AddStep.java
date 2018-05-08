package com.netcracker.ui.service;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Tsits
 */
public class AddStep extends AddStepBasic
{         
    Image img = new Image();
    ComboBox entry_ingredientComboBox = new ComboBox();
    ComboBox output_ingredientComboBox = new ComboBox();
    TextField name = new TextField ();
    TextArea description = new TextArea ();
    Button add = new Button("Добавить");
    Button cancel = new Button("Отмена");

    public class Entry_Ingredient {
        String name;
        int count;

        public Entry_Ingredient() {
        }

        public Entry_Ingredient(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setName(String name) {
            this.name = name;
        }
        
     }
    
    public class Resources {
        String name;

        public Resources() {
        }

        public Resources(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
    }

    public class Output_Ingredient{
        String name;
        int count;

        public Output_Ingredient() {
        }

        public Output_Ingredient(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public String getName() {
            return name;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setName(String name) {
            this.name = name;
        }
        
    }
    
    Grid<Entry_Ingredient> entry_ingredient(){
        // Have some data
        List<Entry_Ingredient> entry_ingredient = Arrays.asList(
        new Entry_Ingredient("Пример",2),
        new Entry_Ingredient("Пример",5),
        new Entry_Ingredient("Пример",5),
        new Entry_Ingredient("Пример",5),
        new Entry_Ingredient("Пример",5),
        new Entry_Ingredient("Пример",5));
        // Create a grid bound to the list
        Grid<Entry_Ingredient> grid = new Grid<>();
        grid.setItems(entry_ingredient);
        grid.addColumn(Entry_Ingredient::getName).setCaption("Name").setWidth(200).setResizable(false);
        grid.addColumn(Entry_Ingredient::getCount).setWidth(70).setResizable(false);
        grid.setWidth("280px");
        grid.setHeight("300px");
        return grid;
    }
     
    Grid<Resources> resources(){
        // Have some data
        List<Resources> resources = Arrays.asList(
        new Resources("Пример"));
        // Create a grid bound to the list
        Grid<Resources> grid = new Grid<>();
        grid.setItems(resources);
        grid.addColumn(Resources::getName).setWidth(200).setResizable(false);
        grid.setWidth("200px");
        grid.setHeight("150px");
        return grid;
    }
    
    Grid<Output_Ingredient> output_ingredient(){
        // Have some data
        List<Output_Ingredient> output_ingredient = Arrays.asList(
        new Output_Ingredient("Пример",2),
        new Output_Ingredient("Пример",5),
        new Output_Ingredient("Пример",5),
        new Output_Ingredient("Пример",5),
        new Output_Ingredient("Пример",5),
        new Output_Ingredient("Пример",5));
        // Create a grid bound to the list
        Grid<Output_Ingredient> grid = new Grid<>();
        grid.setItems(output_ingredient);
        grid.addColumn(Output_Ingredient::getName).setCaption("Name").setWidth(200).setResizable(false);
        grid.addColumn(Output_Ingredient::getCount).setWidth(70).setResizable(false);
        grid.setWidth("280px");
        grid.setHeight("300px");
        return grid;
    }
    
    public AddStep() 
    {        
        super();
        VerticalLayout vl1 = new VerticalLayout();
            vl1.addComponent(new Label("Входные ингредиенты"));
            vl1.addComponent(entry_ingredient());
            entry_ingredientComboBox.setWidth("280px");
            vl1.addComponent(entry_ingredientComboBox);
        VerticalLayout vl2 = new VerticalLayout();
            vl2.addComponent(new Label("Ресурсы"));
            vl2.addComponent(resources());
            vl2.addComponent(name);
            name.setWidth("200px");
            vl2.addComponent(description);
            description.setWidth("200px");
            vl2.addComponent(add);
        VerticalLayout vl3 = new VerticalLayout();
            vl1.addComponent(new Label("Выходные ингредиенты"));
            vl1.addComponent(output_ingredient());
            vl1.addComponent(output_ingredientComboBox);
        HorizontalLayout hl = new HorizontalLayout();
        hl.addComponent(vl2);
        hl.addComponent(vl3);
        super.leftLayout.addComponent(vl1);
        super.centralLayout.addComponent(hl);
        // super.rightLayout.addComponent(vl3);
    }
    
}
