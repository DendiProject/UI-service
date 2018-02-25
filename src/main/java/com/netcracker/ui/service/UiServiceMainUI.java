/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.netcracker.ui.service.exception.menu.component.exception.MenuComponentException;
import com.netcracker.ui.service.menu.component.HandlerForClickingTheButton;
import com.netcracker.ui.service.menu.component.MenusButton;
import com.netcracker.ui.service.menu.component.MenusSearchBar;
import com.netcracker.ui.service.navigator.Navigator;
import com.netcracker.ui.service.navigator.View;
import com.netcracker.ui.service.receipe.ReceipeObserver;
import com.netcracker.ui.service.receipe.ReceipeSubject;
import com.netcracker.ui.service.receipe.ShortViewOfReceipeCreator;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import java.io.File;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Artem
 */
@Theme("centralViewTheme")
@SpringUI
public class UiServiceMainUI extends UI {
    
    //private ResponsiveLayout contentRowLayout;
    //private Navigator navigator;
    
    @Override
    protected void init(VaadinRequest vaadinRequest){
        try
        {
            ReceipeSubject receipeSubject = new ReceipeSubject();
            ReceipeObserver receipeObserver = new ReceipeObserver(1, receipeSubject);
            receipeSubject.getUpdatesForReceipes();
            
            
            
            
            
            
            
            createMainLayout();
            /*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
            ctx.register(AppConfig.class);
            ctx.refresh();
            Employee employee = ctx.getBean(Employee.class);
            System.out.println("Company Name:"+ employee.getCompany().getCompName());
            System.out.println("Location:"+ employee.getCompany().getLocation());
            ctx.close();*/
            
            //Page.getCurrent().setUriFragment(getPage().getUriFragment(), true);
            //reDraw("Main");
        }
        catch(Exception ex)
        {
            Logger logger = LoggerFactory.getLogger(UiServiceMainUI.class);
            logger.info("This is an information message");
            logger.error("this is a error message");
            logger.warn("this is a warning message");
        }
    }
    
    private ResponsiveLayout createMainLayout() throws MenuComponentException
    {
        setSizeFull();//Пользовательский интерфейс на весь экран
        BasicLayoutCreator mainLayer;
        mainLayer = new BasicLayoutCreator();
        ResponsiveLayout mainLayout = mainLayer.mainLayout;
        mainLayout.setSizeFull();
        //mainLayout.setHeight("330%");
        setContent(mainLayout);
        
        //Создание и добавление видов в навигатор
        ArrayList<View> newViews = new ArrayList<>();
        
        newViews.add(new View("Main") {
            @Override
            public void draw() {
                mainLayer.contentRowLayout.removeAllComponents();
                addSliderComponent(mainLayer.contentRowLayout);
                addTopRecepiesComponent(mainLayer.contentRowLayout);
            }
        });

        newViews.add(new View("Recept") {
            @Override
            public void draw() {
                ShortViewOfReceipeCreator shortViewOfReceipe = new ShortViewOfReceipeCreator();
                mainLayer.contentRowLayout.removeAllComponents();
                mainLayer.contentRowLayout = shortViewOfReceipe.create(mainLayer.contentRowLayout);
                //mainLayer.contentRowLayout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("Вы перешли на страницу с рецептами"));
            }
        });
        
        newViews.add(new View("Search") {
            @Override
            public void draw() {
                mainLayer.contentRowLayout.removeAllComponents();
                mainLayer.contentRowLayout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("Рецепты, удовлетворяющие условию поиска:"));
            }
        });
        
        Navigator navigator = new Navigator(getPage(),newViews);
        
        
        //Создаем подпункты меню
        ArrayList<MenusButton> mainSubMenus = new ArrayList<>();
        mainSubMenus.add(new MenusButton("Подпункт1","idsubMain1", new HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                throw new UnsupportedOperationException("No added treatment, yet to test fly an exception for button: subMain1"); //To change body of generated methods, choose Tools | Templates.
            }
        }));
        
        mainSubMenus.add(new MenusButton("Подпункт2","idsubMain2", new HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                throw new UnsupportedOperationException("No added treatment, yet to test fly an exception for button: subMain2"); //To change body of generated methods, choose Tools | Templates.
            }
        }));
        //создаем кнопку меню, включающую подпункты
        MenusButton mainBtn = new MenusButton("Главная","idMain", new  HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                navigator.navigateTo("Main");
            }
            
        },mainSubMenus);
        
        
        //Создаем одноуровневую кнопки меню
        MenusButton recepsBtn = new MenusButton("Рецепты","idRecept", new  HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                navigator.navigateTo("Recept");
            }
            
        });

        MenusSearchBar search = new MenusSearchBar("idSearch", new  HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                navigator.navigateTo("Search");
            }
            
        });
        
        MenusButton inBtn = new MenusButton("Вход","idIn", new  HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                RegistrationForm modalWindow = new RegistrationForm();
                addWindow(modalWindow);
            }

        });
        
        mainLayer.menu.addItem(mainBtn);
        mainLayer.menu.addItem(recepsBtn);
        mainLayer.menu.addItem(search);
        mainLayer.menu.addItem(inBtn);
        
        
        return mainLayer.contentRowLayout;
    }
    
    private void addSliderComponent(ResponsiveLayout contentRowLayout)
    {
        //Создание строки, для добавления конкретного контента на даную страницу
        ResponsiveRow sliderRow = contentRowLayout.addRow();
        //Создание custom слоя для добавления слайдера
        CustomLayout  sliderLayout = new CustomLayout("SliderLayout");
        sliderRow.addColumn().withDisplayRules(12, 12, 12, 12).withComponent(sliderLayout);
    }
    
    private void addTopRecepiesComponent(ResponsiveLayout contentRowLayout)
    {
        //Отрисовка заголовка топа рецептов
        ResponsiveRow recipeTitle = contentRowLayout.addRow();
        CustomLayout  topRecipeTitleLayout = new CustomLayout("TopRecipeTitle");
        recipeTitle.addColumn().withDisplayRules(12, 12, 12, 12).withComponent(topRecipeTitleLayout);
        //Здесь можно разместить добавление рецептов либо фиксированно, например, топ 5, или
        //Задать количество по какому-либо другому параметру, например, по нажатию кнопки добавлять
        //еще несколько к имеющемуся списку
        for(int i=0;i<8;i++)
        {
            //Задание отступа между рецептами
            ResponsiveRow theDistanceBetweenRecipe = contentRowLayout.addRow();
            theDistanceBetweenRecipe.setHeight("30px");
            theDistanceBetweenRecipe.addColumn().withDisplayRules(12, 12, 12, 12);
            //Отрисовка изображения рецепта
            ResponsiveRow recipeRow = contentRowLayout.addRow();
            Image topImage = new Image();
            topImage.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/top1.png")));
            topImage.setHeight("70%");
            topImage.setWidth("100%");
            recipeRow.addColumn().withDisplayRules(2, 2, 2, 2).withComponent(topImage);
            //Создание custom макета с поддержкой flexbox
            CustomLayout  topRecipeLayout = new CustomLayout("TopRecipeLayout");
            recipeRow.addColumn().withDisplayRules(8, 8, 8, 8).withComponent(topRecipeLayout);

            //Задание информации по каждому из рецептов
            Label recipesName = new Label("Название");
            topRecipeLayout.addComponent(recipesName,"recipes_name");
            Label recipesAuthor = new Label("Автор");
            topRecipeLayout.addComponent(recipesAuthor,"recipes_author");
            Button recepiesPartsButton = new Button("Ингридиенты");
            topRecipeLayout.addComponent(recepiesPartsButton,"parts_recipe_button");
            Label numberOfServingsLable = new Label(String.valueOf(i));//просто для примера
            topRecipeLayout.addComponent(numberOfServingsLable,"number_of_servings_lable");
            Label workingTimesLable = new Label(String.valueOf(i));//просто для примера
            topRecipeLayout.addComponent(workingTimesLable,"working_times_lable");
            Button addRecipeToFavoritesButton = new Button("Добавить в избранное");
            topRecipeLayout.addComponent(addRecipeToFavoritesButton,"add_recipe_to_favorites_button");
        }

        //Задание отступа до коцна страницы
        ResponsiveRow theDistanceBetweenBottomAndRecipes = contentRowLayout.addRow();
        theDistanceBetweenBottomAndRecipes.setHeight("60px");
        theDistanceBetweenBottomAndRecipes.addColumn().withDisplayRules(12, 12, 12, 12);
    }
}
