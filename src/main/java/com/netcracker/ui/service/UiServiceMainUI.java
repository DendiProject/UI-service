/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import com.netcracker.ui.service.exception.navigator.InvalidQueryFormat;
import com.netcracker.ui.service.exception.navigator.NoViewAvailable;
import com.netcracker.ui.service.forms.RegistrationForm;
import com.netcracker.ui.service.forms.AuthorizationForm;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.buttonsClickListener.component.ButtonsClickListener;
import com.netcracker.ui.service.buttonsClickListener.component.ClickListener;
import com.netcracker.ui.service.content.handler.ContentManagerController;
import com.netcracker.ui.service.content.handler.CookieHandler;
import com.netcracker.ui.service.content.handler.JWTHandler;
import com.netcracker.ui.service.exception.ConcreteException;
import com.netcracker.ui.service.exception.ConcreteExceptionHandler;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.exception.UiServiceException;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netcracker.ui.service.exception.importanceTypes.BasicImportanceClass;
import com.netcracker.ui.service.exception.menu.component.exception.MenuComponentException;
import com.netcracker.ui.service.exception.navigator.InternalServerError;
import com.netcracker.ui.service.exception.navigator.NotFound;
import com.netcracker.ui.service.graf.component.Edge;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;
import com.netcracker.ui.service.menu.component.HandlerForClickingTheButton;
import com.netcracker.ui.service.menu.component.MenusButton;
import com.netcracker.ui.service.menu.component.MenusSearchBar;
import com.netcracker.ui.service.navigator.Navigator;
import com.netcracker.ui.service.navigator.View;
import com.netcracker.ui.service.receipe.view.basic.objects.ReceipeDataConverter;
import com.netcracker.ui.service.receipe.view.basic.objects.ReceipeProxy;
import com.netcracker.ui.service.receipe.view.basic.objects.ReceipeStore;
import com.netcracker.ui.service.receipe.view.basic.objects.ReceipeView;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
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
import org.springframework.util.LinkedMultiValueMap;
import com.vaadin.ui.TextArea;
import java.util.List;
import javax.servlet.http.Cookie;
import com.netcracker.ui.service.receipe.view.basic.objects.Resource;
/**
 *
 * @author Artem
 */
@Theme("centralViewTheme")
@SpringUI
public class UiServiceMainUI extends UI{
    
    BeansFactory<ContentManagerController> bfCMC = BeansFactory.getInstance();
    ContentManagerController contentManadgerController;
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        GMFacade gm = new GMFacade("http://localhost:8083/");
        Node n = new Node("", "description", "picture");
        n.setLabel("label");
        Node node = gm.getGmNodeFacade().addNode(n);
        Edge edge = new Edge();
        gm.getGmEdgeFacade().addEdge(edge);
        List<Resource> resources = new ArrayList<>();
        Resource resource1 = new Resource("id", "", "name", 2);
        Resource resource2 = new Resource("id2", "id", "name2", 4);
        
        resources.add(resource1);
        resources.add(resource2);
        gm.getGmNodeFacade().addInputResources(node, resources);
        gm.getGmNodeFacade().addOutputResources(node, resources);
        gm.getGmNodeFacade().getInputResources(node, "resource");
        gm.getGmNodeFacade().deleteNode(node);
        gm.getGmEdgeFacade().deleteEdge(edge);
        gm.getGmGrafFacade().getTestGraf("1111", "111111");
        String catalogId = gm.getGmCatalogFacade().createCatalog("name", "description");
        String catalogId2 = gm.getGmCatalogFacade().getCatalogsId("name");
        String catalogDescription = gm.getGmCatalogFacade().getCatalogsDescription("name");
        int d=0;
        
        try {
            CookieHandler ch = new CookieHandler();
            JWTHandler jwth = new JWTHandler();
            Cookie userCookie = ch.getCookieByName("userInfo");
            if (userCookie == null) {
              createMainLayout();
            } else {
              boolean user = jwth.checkUser(userCookie.getValue(), "test");
              if (user) {
                createUserLayout();
              } else {
                createMainLayout();
              }
            }
          } 
        catch (Exception exception) {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
    }
    
    private void setUrl(String path)
    {
        getPage().setUriFragment(path);
    }
    
    private String getUrl()
    {
        return getPage().getUriFragment();
    }
    
    private ResponsiveLayout createMainLayout() throws MenuComponentException, 
            NotFoundBean
    {
        BasicLayoutCreator mainLayer;
        mainLayer = new BasicLayoutCreator();
        setSizeFull();//Пользовательский интерфейс на весь экран
        ResponsiveLayout mainLayout = mainLayer.mainLayout;
        mainLayout.setStyleName("teststyle");

        mainLayout.setSizeFull();
        setContent(mainLayout);

        //Создание и добавление видов в навигатор
        ArrayList<View> newViews = new ArrayList<>();

        newViews.add(new View("Main") {
            @Override
            public void draw(LinkedMultiValueMap<String, String> parameters) {
                mainLayer.contentRowLayout.removeAllComponents();
                addSliderComponent(mainLayer.contentRowLayout);
                addTopRecepiesComponent(mainLayer.contentRowLayout);
            }
        });

        newViews.add(new View("PageNotFound") {
            @Override
            public void draw(LinkedMultiValueMap<String, String> parameters) {
                mainLayer.contentRowLayout.removeAllComponents();
                mainLayer.contentRowLayout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("404"));
            }
        });

        newViews.add(new View("PageInternalServerError") {
            @Override
            public void draw(LinkedMultiValueMap<String, String> parameters) {
                mainLayer.contentRowLayout.removeAllComponents();
                mainLayer.contentRowLayout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("500"));
            }
        });

        newViews.add(new View("Recept") {
            @Override
            public void draw(LinkedMultiValueMap<String, String> parameters) {               
                /*MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
                parameters.add("receipeId", "12345");
                parameters.add("userId","1111");*/
                ReceipeProxy proxy = new ReceipeProxy();
                proxy.setConfig("http://localhost:8083/graph/gettestgraph", parameters.getFirst("userId"), parameters.getFirst("receipeId"));

                ReceipeDataConverter converter = new ReceipeDataConverter();
                ReceipeStore store = new ReceipeStore(converter);

                ReceipeView view = new ReceipeView(proxy, store);
                try
                {
                    view.reload();
                    mainLayer.contentRowLayout.removeAllComponents();
                    mainLayer.contentRowLayout = view.drawReceipe(mainLayer.contentRowLayout);
                }
                catch(Exception exception)
                {
                    //ДОБАВИТЬ БИН HttpClientErrorException
                    //ДОБАВИТЬ БИН NullPointerException
                    ExceptionHandler.getInstance().runExceptionhandling(exception);
                }
            }
        });

        newViews.add(new View("Search") {
            @Override
            public void draw(LinkedMultiValueMap<String, String> parameters) {
                mainLayer.contentRowLayout.removeAllComponents();
                mainLayer.contentRowLayout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("Рецепты, удовлетворяющие условию поиска:"));
            }
        });

        newViews.add(new View("UserPage") {
           @Override
            public void draw(LinkedMultiValueMap<String, String> parameters) {
                CustomLayout ShortViewOfReceipeLayout = new CustomLayout("UserPageLayout");
                ShortViewOfReceipeLayout.setHeight("100%");
                mainLayer.contentRowLayout.setHeight("100%");
                mainLayer.contentRowLayout.addComponent(ShortViewOfReceipeLayout);
            }
        });
        try
        {
            Navigator navigator = new Navigator(newViews, "Main", getPage());
            ExceptionHandler ex = ExceptionHandler.getInstance(); 
            ConcreteException notFoundException = 
                    new ConcreteException(new ConcreteExceptionHandler() {
                        @Override
                        public void handling(Exception exception) {
                            try {
                                navigator.navigateTo("PageNotFound");
                            } catch (Exception ex1) {

                            }
                        }
                    }, NotFound.class, "", "Page not found.",
                    BasicImportanceClass.informationMessage);
            ex.addException(notFoundException);

            ConcreteException internalServerErrorException = 
                    new ConcreteException(new ConcreteExceptionHandler() {
                        @Override
                        public void handling(Exception exception) {
                            try {
                                navigator.navigateTo("PageInternalServerError");
                            } catch (Exception ex1) {

                            }
                        }
                    }, InternalServerError.class, "", "Internal server error.",
                    BasicImportanceClass.informationMessage);
            ex.addException(internalServerErrorException);

            getPage().addUriFragmentChangedListener(
            new Page.UriFragmentChangedListener() {
                public void uriFragmentChanged(
                    Page.UriFragmentChangedEvent source) {
                        try{
                            navigator.navigateTo(getUrl());
                        }
                        catch(Exception exception)
                        {
                            ExceptionHandler.getInstance().
                                    runExceptionhandling(exception);
                        }
                }
            });

            //navigator.load();
            navigator.navigateTo(getUrl());
        }
        //Следующий набор catch исправлю, сделал так (временно), чтобы здесь не 
        //обрабатывалось исключение 500 ошибки
        catch (NoViewAvailable exception) {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
        catch(InvalidQueryFormat ex){
            ExceptionHandler.getInstance().runExceptionhandling(ex);
        }
        catch(NotFound ex){
            ExceptionHandler.getInstance().runExceptionhandling(ex);
        }
        //Создаем подпункты меню
        ArrayList<MenusButton> mainSubMenus = new ArrayList<>();
        mainSubMenus.add(new MenusButton("Подпункт1","idsubMain1", new HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                try {
                    throw new UiServiceException("Проверка работы с неизвестным "
                            + "типом исключений");
                }
                catch(Exception exception)
                {
                    ExceptionHandler.getInstance().runExceptionhandling(exception);
                }
            }
        }));

        mainSubMenus.add(new MenusButton("Подпункт2","idsubMain2", new HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                try {
                    throw new UiServiceException("Проверка работы с неизвестным "
                            + "типом исключений");
                }
                catch(Exception exception)
                {
                    ExceptionHandler.getInstance().runExceptionhandling(exception);
                }
            }
        }));
        //создаем кнопку меню, включающую подпункты
        MenusButton mainBtn = new MenusButton("Главная","idMain", new  HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                setUrl("Main");
            }

        },mainSubMenus);


        //Создаем одноуровневую кнопки меню
        MenusButton recepsBtn = new MenusButton("Рецепты","idRecept", new  HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                setUrl("Recept?userId=1111&receipeId=12343");
            }

        });

        MenusSearchBar search = new MenusSearchBar("idSearch", new  HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                setUrl("Search");
            }

        });

        MenusButton registration = new MenusButton("Регистрация", "idregistration", new  HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                RegistrationForm modalWindow = new RegistrationForm();
                addWindow(modalWindow);
            }

        });

        MenusButton signIn = new MenusButton("Войти", "idSignin", new  HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                AuthorizationForm modalWindow = new AuthorizationForm();
                addWindow(modalWindow);
            }

        });

        MenusButton userPageBtn = new MenusButton("Профиль","iduserPage", new  HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
               setUrl("UserPage");
            }

        });

        mainLayer.menu.addItem(mainBtn);
        mainLayer.menu.addItem(recepsBtn);
        mainLayer.menu.addItem(search);
        mainLayer.menu.addItem(registration);
        mainLayer.menu.addItem(signIn);

        BeansFactory<ButtonsClickListener> bf = BeansFactory.getInstance();
        ButtonsClickListener clickListener;

        clickListener = bf.getBean(ButtonsClickListener.class);
        clickListener.addButtonClickListener(new ClickListener() {
            @Override
            public String getId() {
                return "addReceipePartsBtn";
            }

            @Override
            public void onEventDo() {
                AuthorizationForm modalWindow = new AuthorizationForm();
                addWindow(modalWindow);
            }
        });
        clickListener.addButtonClickListener(new ClickListener() {
            @Override
            public String getId() {
                return "addReceipeResoursesBtn";
            }

            @Override
            public void onEventDo() {
                int i=0;
            }
        });
        return mainLayer.contentRowLayout;
    }
    
      private ResponsiveLayout createUserLayout() throws MenuComponentException, 
            NotFoundBean
    {
        BasicLayoutCreator mainLayer;
        mainLayer = new BasicLayoutCreator();
        setSizeFull();//Пользовательский интерфейс на весь экран
        ResponsiveLayout mainLayout = mainLayer.mainLayout;
        mainLayout.setStyleName("teststyle");

        mainLayout.setSizeFull();
        setContent(mainLayout);

        //Создание и добавление видов в навигатор
        ArrayList<View> newViews = new ArrayList<>();

        newViews.add(new View("Main") {
            @Override
            public void draw(LinkedMultiValueMap<String, String> parameters) {
                mainLayer.contentRowLayout.removeAllComponents();
                addSliderComponent(mainLayer.contentRowLayout);
                addTopRecepiesComponent(mainLayer.contentRowLayout);
            }
        });

        newViews.add(new View("PageNotFound") {
            @Override
            public void draw(LinkedMultiValueMap<String, String> parameters) {
                mainLayer.contentRowLayout.removeAllComponents();
                mainLayer.contentRowLayout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("404"));
            }
        });

        newViews.add(new View("PageInternalServerError") {
            @Override
            public void draw(LinkedMultiValueMap<String, String> parameters) {
                mainLayer.contentRowLayout.removeAllComponents();
                mainLayer.contentRowLayout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("500"));
            }
        });

        newViews.add(new View("Recept") {
            @Override
            public void draw(LinkedMultiValueMap<String, String> parameters) {               
                /*MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
                parameters.add("receipeId", "12345");
                parameters.add("userId","1111");*/
                ReceipeProxy proxy = new ReceipeProxy();
                proxy.setConfig("http://localhost:8083/graph/gettestgraph", parameters.getFirst("userId"), parameters.getFirst("receipeId"));

                ReceipeDataConverter converter = new ReceipeDataConverter();
                ReceipeStore store = new ReceipeStore(converter);

                ReceipeView view = new ReceipeView(proxy, store);
                try
                {
                    view.reload();
                    mainLayer.contentRowLayout.removeAllComponents();
                    mainLayer.contentRowLayout = view.drawReceipe(mainLayer.contentRowLayout);
                }
                catch(Exception exception)
                {
                    //ДОБАВИТЬ БИН HttpClientErrorException
                    //ДОБАВИТЬ БИН NullPointerException
                    ExceptionHandler.getInstance().runExceptionhandling(exception);
                }
            }
        });

        newViews.add(new View("Search") {
            @Override
            public void draw(LinkedMultiValueMap<String, String> parameters) {
                mainLayer.contentRowLayout.removeAllComponents();
                mainLayer.contentRowLayout.addRow().addColumn().withDisplayRules(12, 12, 12, 12).withComponent(new Label("Рецепты, удовлетворяющие условию поиска:"));
            }
        });

        newViews.add(new View("UserPage") {
           @Override
            public void draw(LinkedMultiValueMap<String, String> parameters) {
                CustomLayout ShortViewOfReceipeLayout = new CustomLayout("UserPageLayout");
                ShortViewOfReceipeLayout.setHeight("100%");
                mainLayer.contentRowLayout.setHeight("100%");
                mainLayer.contentRowLayout.addComponent(ShortViewOfReceipeLayout);
                ShortViewOfReceipeLayout.addComponent(new Label("Name"), "userPageNameFieldAndLable");
                ShortViewOfReceipeLayout.addComponent(new Label("SecondName"), "userPageSecondNameFieldAndLable");
                ShortViewOfReceipeLayout.addComponent(new Label("Nickname"), "userPageNicknameFieldAndLable");
                ShortViewOfReceipeLayout.addComponent(new Label("Mail"), "userPageMailFieldAndLable");
                ShortViewOfReceipeLayout.addComponent(new Label("BirthDate"), "userPageBirthDateFieldAndLable");
                TextArea area = new TextArea();
                area.setValue("testt esttestte sttesttesttesttest testtesttest"
                        + "testtestt esttesttesttesttesttesttesttesttest"
                        + "testt esttesttesttesttest testtestt esttesttest"
                        + "testtesttestte sttest testtest testtesttest"
                        + "testtestt esttest testtesttesttesttest");
                area.setHeight("100%");
                area.setWidth("100%");
                area.setWordWrap(true);
                ShortViewOfReceipeLayout.addComponent(area, "userPageAboutOneselfFieldAndLable");
                
                Image topImage = new Image();
                topImage.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/cake.png")));
                topImage.setHeight("100%");
                topImage.setWidth("100%");
                ShortViewOfReceipeLayout.addComponent(topImage, "userPageImage");
            }
        });
        try
        {
            Navigator navigator = new Navigator(newViews, "Main", getPage());
            ExceptionHandler ex = ExceptionHandler.getInstance(); 
            ConcreteException notFoundException = 
                    new ConcreteException(new ConcreteExceptionHandler() {
                        @Override
                        public void handling(Exception exception) {
                            try {
                                navigator.navigateTo("PageNotFound");
                            } catch (Exception ex1) {

                            }
                        }
                    }, NotFound.class, "", "Page not found.",
                    BasicImportanceClass.informationMessage);
            ex.addException(notFoundException);

            ConcreteException internalServerErrorException = 
                    new ConcreteException(new ConcreteExceptionHandler() {
                        @Override
                        public void handling(Exception exception) {
                            try {
                                navigator.navigateTo("PageInternalServerError");
                            } catch (Exception ex1) {

                            }
                        }
                    }, InternalServerError.class, "", "Internal server error.",
                    BasicImportanceClass.informationMessage);
            ex.addException(internalServerErrorException);

            getPage().addUriFragmentChangedListener(
            new Page.UriFragmentChangedListener() {
                public void uriFragmentChanged(
                    Page.UriFragmentChangedEvent source) {
                        try{
                            navigator.navigateTo(getUrl());
                        }
                        catch(Exception exception)
                        {
                            ExceptionHandler.getInstance().
                                    runExceptionhandling(exception);
                        }
                }
            });

            //navigator.load();
            navigator.navigateTo(getUrl());
        }
        //Следующий набор catch исправлю, сделал так (временно), чтобы здесь не 
        //обрабатывалось исключение 500 ошибки
        catch (NoViewAvailable exception) {
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
        catch(InvalidQueryFormat ex){
            ExceptionHandler.getInstance().runExceptionhandling(ex);
        }
        catch(NotFound ex){
            ExceptionHandler.getInstance().runExceptionhandling(ex);
        }
        //Создаем подпункты меню
        ArrayList<MenusButton> mainSubMenus = new ArrayList<>();
        mainSubMenus.add(new MenusButton("Подпункт1","idsubMain1", new HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                try {
                    throw new UiServiceException("Проверка работы с неизвестным "
                            + "типом исключений");
                }
                catch(Exception exception)
                {
                    ExceptionHandler.getInstance().runExceptionhandling(exception);
                }
            }
        }));

        mainSubMenus.add(new MenusButton("Подпункт2","idsubMain2", new HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                try {
                    throw new UiServiceException("Проверка работы с неизвестным "
                            + "типом исключений");
                }
                catch(Exception exception)
                {
                    ExceptionHandler.getInstance().runExceptionhandling(exception);
                }
            }
        }));
        //создаем кнопку меню, включающую подпункты
        MenusButton mainBtn = new MenusButton("Главная","idMain", new  HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                setUrl("Main");
            }

        },mainSubMenus);


        //Создаем одноуровневую кнопки меню
        MenusButton recepsBtn = new MenusButton("Рецепты","idRecept", new  HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                setUrl("Recept?userId=1111&receipeId=12343");
            }

        });

        MenusSearchBar search = new MenusSearchBar("idSearch", new  HandlerForClickingTheButton(){
            @Override
            public void onEventClickDo() {
                setUrl("Search");
            }

        });

        MenusButton userPageBtn = new MenusButton("Профиль", "iduserPage", new HandlerForClickingTheButton() {
        @Override
        public void onEventClickDo() {
          getPage().setUriFragment("UserPage");
        }

      });

      MenusButton exitBtn = new MenusButton("Выход", "idExit", new HandlerForClickingTheButton() {
        @Override
        public void onEventClickDo() {
          CookieHandler ch = new CookieHandler();
          ch.getCookieForGuest();
          getPage().setUriFragment("Main");
          Page.getCurrent().reload();
        }
      });

      mainLayer.menu.addItem(mainBtn);
      mainLayer.menu.addItem(recepsBtn);
      mainLayer.menu.addItem(search);
      mainLayer.menu.addItem(userPageBtn);
      mainLayer.menu.addItem(exitBtn);

        BeansFactory<ButtonsClickListener> bf = BeansFactory.getInstance();
        ButtonsClickListener clickListener;

        clickListener = bf.getBean(ButtonsClickListener.class);
        clickListener.addButtonClickListener(new ClickListener() {
            @Override
            public String getId() {
                return "addReceipePartsBtn";
            }

            @Override
            public void onEventDo() {
                AuthorizationForm modalWindow = new AuthorizationForm();
                addWindow(modalWindow);
            }
        });
        clickListener.addButtonClickListener(new ClickListener() {
            @Override
            public String getId() {
                return "addReceipeResoursesBtn";
            }

            @Override
            public void onEventDo() {
                int i=0;
            }
        });
        
        //Артем, назначь действия на событие onClick для следующих кнопок:
        clickListener.addButtonClickListener(new ClickListener() {
            @Override
            public String getId() {
                return "userPageSaveBtn";
            }

            @Override
            public void onEventDo() {
                int i=0;//Код писать сюда
            }
        });
        clickListener.addButtonClickListener(new ClickListener() {
            @Override
            public String getId() {
                return "userPageCancelBtn";
            }

            @Override
            public void onEventDo() {
                int i=0;//Код писать сюда
            }
        });
        clickListener.addButtonClickListener(new ClickListener() {
            @Override
            public String getId() {
                return "userPageLoadFotoBtn";
            }

            @Override
            public void onEventDo() {
                int i=0;//Код писать сюда
            }
        });
        clickListener.addButtonClickListener(new ClickListener() {
            @Override
            public String getId() {
                return "userPageChangePasswordButtonBtn";
            }

            @Override
            public void onEventDo() {
                int i=0;//Код писать сюда
            }
        });
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
            topImage.setSource(new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/images/cake.png")));
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