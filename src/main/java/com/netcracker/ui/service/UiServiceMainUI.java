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
import com.netcracker.ui.service.buttonsClickListener.component.SessionStorageHelper;
import com.netcracker.ui.service.components.PostUserData;
import com.netcracker.ui.service.components.Properties;
import com.netcracker.ui.service.security.SecurityTokenHandler;

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

import com.netcracker.ui.service.forms.ExitForm;

import com.netcracker.ui.service.forms.AddStepForm;
import com.netcracker.ui.service.forms.CreateReceipeForm;

import com.netcracker.ui.service.forms.NoReadyReceipeForm;
import com.netcracker.ui.service.forms.listeners.CreateReceipeListener;
import com.netcracker.ui.service.graf.component.Edge;
import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.gmfacade.GMFacade;
import com.netcracker.ui.service.menu.component.HandlerForClickingTheButton;
import com.netcracker.ui.service.menu.component.MenusButton;
import com.netcracker.ui.service.menu.component.MenusSearchBar;
import com.netcracker.ui.service.navigator.Navigator;
import com.netcracker.ui.service.navigator.View;


import com.netcracker.ui.service.receipe.view.basic.objects.Catalog;

import com.netcracker.ui.service.receipe.view.basic.objects.Receipe;
import com.netcracker.ui.service.receipe.view.basic.objects.ReceipeDataConverter;
import com.netcracker.ui.service.receipe.view.basic.objects.ReceipeProxy;
import com.netcracker.ui.service.receipe.view.basic.objects.ReceipeStore;
import com.netcracker.ui.service.receipe.view.basic.objects.ReceipeView;
import com.netcracker.ui.service.receipe.view.basic.objects.Resource;
import com.netcracker.ui.service.receipe.view.basic.objects.ShowReceipeView;
import com.netcracker.ui.service.views.CreateRecipeView;
import com.vaadin.annotations.Theme;
import com.vaadin.server.ExternalResource;
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
import java.util.ArrayList;
import org.springframework.util.LinkedMultiValueMap;
import com.vaadin.ui.TextArea;

import java.util.logging.Level;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.Cookie;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import java.io.File;


/**
 *
 * @author Artem
 */
@Theme("centralViewTheme")
@SpringUI
public class UiServiceMainUI extends UI {

  BeansFactory<ContentManagerController> bfCMC = BeansFactory.getInstance();
  ContentManagerController contentManadgerController;
  
  @Override
  protected void init(VaadinRequest vaadinRequest) {
    try {
      /*CookieHandler ch2 = new CookieHandler();
        JWTHandler jwth2 = new JWTHandler();
        Cookie userCookie2 = ch2.getCookieByName("userInfo");
        String userid = jwth2.readUserId(userCookie2.getValue(), "test");
        //String userid = "111111111111111";
        GMFacade gm = new GMFacade("http://localhost:8083/");
        /*Node n = new Node("", "description", "picture");
        n.setLabel("label");
        Node n2 = new Node("", "description2", "picture2");
        n.setLabel("label");
        n2.setLabel("label2");*/
        /*List<Resource> resources = new ArrayList<>();
        Resource resource1 = new Resource("id", "id", "name222", 2, "литры", "picture", "resource");
        Resource resource2 = new Resource("id2", "id", "name444", 4, "литры", "picture", "ingredient");
        Resource resource3 = new Resource("id2", "id", "name888", 4, "литры", "picture", "resource");
        resources.add(resource1);
        resources.add(resource2);
        resources.add(resource3);
        resource1.setResourceId(gm.getGmResourceFacade().addResource(resource1.getName(),resource1.getIngredientOrResource(),resource1.getMeasuring(), "user",resource1.getPictureId()));
        resource2.setResourceId(gm.getGmResourceFacade().addResource(resource2.getName(),resource2.getIngredientOrResource(),resource2.getMeasuring(), "user",resource2.getPictureId()));
        resource3.setResourceId(gm.getGmResourceFacade().addResource(resource3.getName(),resource3.getIngredientOrResource(),resource3.getMeasuring(), "user",resource3.getPictureId()));
        //List<ShortResource> loaddresource = gm.getGmResourceFacade().getResourcesByLetters("nam", "resource", 5);
        //List<Resource> testgetAllRes = gm.getGmResourceFacade().getResources(false);*/
        //String catalogId = gm.getGmCatalogFacade().createCatalog("for receipe22222", "description");
        //String receipeid = gm.getGmReceipeFacade().addReceipe("gjgjgjgjgjgj", "jdggdgdg", catalogId,userid, true).getReceipeId();
        /*String receiperes = gm.getGmReceipeFacade().addReceipeResource(receipeid, userid, resource1.getResourceId(), 5);
        Node node = gm.getGmNodeFacade().addNode(n,receipeid, userid);
        Node node2 = gm.getGmNodeFacade().addNode(n2,receipeid, userid);
        gm.getGmNodeFacade().addInputResources(node, resources);
        gm.getGmNodeFacade().addOutputResources(node, resources);
        List<Resource> testregdg = gm.getGmNodeFacade().getInputResources(node, "resource");
        List<Resource> testregdggdgdgd = gm.getGmNodeFacade().getOutputResources(node, "resource");
        Edge edge = new Edge(node.getNodeId(), node2.getNodeId());
        gm.getGmEdgeFacade().addEdge(edge);
        gm.getGmReceipeFacade().setReceipeCompleted(receipeid);*/
        //ReceipeInformation receipeInformation = gm.getGmReceipeFacade().getReceipeInfo("026de89d-c3de-4981-b198-900335dc550a");
        /*List<ShortReceipe> loadingReceipe = gm.getGmReceipeFacade().getPublicAndCompletesReceipesByCatalogId(catalogId, 5);
        gm.getGmTagFacade().addTagToReceipe(receipeid, "name");
        //List<ShortReceipe> gdg = gm.getGmTagFacade().getReceipesByTag("name", 5);
        //List<Tag> gdggdgdg = gm.getGmTagFacade().getTagsByLetters("nam", 5);
        //gm.getGmNodeFacade().deleteNode(node);
        gm.getGmEdgeFacade().deleteEdge(edge);
        JSONObject gdgdhhgdgdgd = gm.getGmGrafFacade().getGraph(userid, receipeid);
        JSONObject gdgdhhgdgdgd2 = gm.getGmGrafFacade().getParallelGraph(userid, receipeid);
        gm.getGmReceipeFacade().deleteReceipe(receipeid, userid);
        JSONObject gdgdhh = gm.getGmGrafFacade().getTestGraf("1111", "111111");
        //Catalog catalog = gm.getGmCatalogFacade().getCatalog("for receipe22222");
        int d=0;*/


      createMainLayout();
    } catch (Exception exception) {
      ExceptionHandler.getInstance().runExceptionhandling(exception);
    }
  }
  
   private String getUserID(){
        CookieHandler ch2 = new CookieHandler();
        JWTHandler jwth2 = new JWTHandler();
        Cookie userCookie2 = ch2.getCookieByName("userInfo");
        String userid = jwth2.readUserId(userCookie2.getValue(), "test");
        return userid;
    }
  
  private void setUrl(String path) {
    getPage().setUriFragment(path);
  }

  private String getUrl() {
    return getPage().getUriFragment();
  }

  private ResponsiveLayout createMainLayout() throws MenuComponentException,
          NotFoundBean {
    BasicLayoutCreator mainLayer;
    mainLayer = new BasicLayoutCreator();
    setSizeFull();//Пользовательский интерфейс на весь экран
    ResponsiveLayout mainLayout = mainLayer.mainLayout;
    mainLayout.setStyleName("teststyle");

    mainLayout.setSizeFull();
    setContent(mainLayout);
    TextField fName = new TextField();
    TextField sName = new TextField();
    TextField email = new TextField();
    //Создание и добавление видов в навигатор
    ArrayList<View> newViews = new ArrayList<>();

    newViews.add(new View("Main") {
      @Override
      public void draw(LinkedMultiValueMap<String, String> parameters) {
        mainLayer.contentRowLayout.removeAllComponents();
        addSliderComponent(mainLayer.contentRowLayout);
        try {
          addTopRecepiesComponent(mainLayer.contentRowLayout);
        } catch (Exception ex) {
          java.util.logging.Logger.getLogger(UiServiceMainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    newViews.add(new View("RecipeConfigurator") {
            @Override
            public void draw(LinkedMultiValueMap<String, String> parameters) {                
                //Получение id пользователя
                CookieHandler ch2 = new CookieHandler();
                JWTHandler jwth2 = new JWTHandler();
                Cookie userCookie2 = ch2.getCookieByName("userInfo");
                String userId = jwth2.readUserId(userCookie2.getValue(), 
                        "test");
                
                if(!userId.equals("1")){
                    mainLayer.contentRowLayout.removeAllComponents();
                    mainLayer.contentRowLayout.setHeight("100%");
                    String noFinishRecipeId = checkNonFinishRecipe(userId);
                    if(noFinishRecipeId == null | noFinishRecipeId.equals("")){
                        //Если нет незаконченного рецепта
                        CreateRecipeView createRecipeView = new CreateRecipeView(
                            new CreateReceipeListener() {
                            @Override
                            public void onCreate(String recipeId, 
                                    String userId) {
                                ReceipeProxy proxy = new ReceipeProxy();
                                proxy.setConfig(
                                        "http://localhost:8083/", 
                                        userId, recipeId, true);

                                ReceipeDataConverter converter = 
                                        new ReceipeDataConverter();
                                ReceipeStore store = new ReceipeStore(converter);

                                ReceipeView view = new ReceipeView(proxy, store);
                                Receipe emtyReceipe = new Receipe("", 
                                        new ArrayList<Node>(), new ArrayList<Edge>(),
                                        new ArrayList<Resource>(), 
                                        new ArrayList<Resource>(), false);
                                view.setNewViewsData(emtyReceipe);
                                mainLayer.contentRowLayout.
                                        removeAllComponents();
                                mainLayer.contentRowLayout = view.
                                        drawReceipe(
                                        mainLayer.contentRowLayout, (form) 
                                                -> {addWindow(form);
                                });
                            }
                        });
                        mainLayer.contentRowLayout.addComponent(
                                createRecipeView.create());
                    }
                    else{
                        //Если есть незаконченный рецепт, то вызов окна
                        NoReadyReceipeForm noReadyReceipeForm = new 
                            NoReadyReceipeForm((resume, recipeId) -> {
                                if(resume == true){
                                    ReceipeProxy proxy = new ReceipeProxy();
                                    proxy.setConfig("http://localhost:8083/", 
                                            userId, recipeId, true);

                                    ReceipeDataConverter converter = 
                                            new ReceipeDataConverter();
                                    ReceipeStore store = new ReceipeStore(converter);

                                    ReceipeView view = new ReceipeView(proxy, store);
                                    try{
                                        view.reload();
                                        mainLayer.contentRowLayout.
                                                removeAllComponents();
                                        mainLayer.contentRowLayout = view.
                                                drawReceipe(
                                                mainLayer.contentRowLayout, 
                                                    (form) -> {addWindow(form);
                                        });
                                    }
                                    catch(Exception exception){
                                        //ДОБАВИТЬ БИН HttpClientErrorException
                                        //ДОБАВИТЬ БИН NullPointerException
                                        ExceptionHandler.getInstance().
                                                runExceptionhandling(exception);
                                    }
                                }
                                else{
                                    //Иначе удаляем незаконченный граф
                                    try{
                                        BeansFactory<GMFacade> bf = BeansFactory.getInstance();
                                        GMFacade gmFacade = bf.getBean(GMFacade.class);
                                        gmFacade.getGmReceipeFacade().deleteReceipe(
                                                recipeId, userId);

                                        CreateRecipeView createRecipeView = 
                                        new CreateRecipeView(
                                        new CreateReceipeListener() {
                                        @Override
                                        public void onCreate(String recipeId, 
                                                String userId) {
                                            ReceipeProxy proxy = new ReceipeProxy();
                                            proxy.setConfig(
                                                    "http://localhost:8083/", 
                                                    userId, recipeId, true);

                                            ReceipeDataConverter converter = 
                                                    new ReceipeDataConverter();
                                            ReceipeStore store = 
                                                    new ReceipeStore(converter);

                                            ReceipeView view = 
                                                    new ReceipeView(proxy, store);
                                            Receipe emtyReceipe = new Receipe("", 
                                                    new ArrayList<Node>(), 
                                                    new ArrayList<Edge>(),
                                                    new ArrayList<Resource>(), 
                                                    new ArrayList<Resource>(), false);
                                                view.setNewViewsData(emtyReceipe);
                                                mainLayer.contentRowLayout.
                                                        removeAllComponents();
                                                mainLayer.contentRowLayout = view.
                                                        drawReceipe(
                                                        mainLayer.contentRowLayout, (form) 
                                                                -> {addWindow(form);
                                                });
                                        }
                                    });
                                    mainLayer.contentRowLayout.addComponent(
                                            createRecipeView.create());
                                    }
                                    catch(Exception exception){
                                        ExceptionHandler.getInstance().runExceptionhandling(exception);
                                    }  
                                }
                        }, noFinishRecipeId);
                        addWindow(noReadyReceipeForm);
                    }
                }
                else
                {
                    AuthorizationForm modalWindow = new AuthorizationForm(UI.getCurrent());
                    addWindow(modalWindow);
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
                mainLayer.contentRowLayout.removeAllComponents();
                CustomLayout ShortViewOfReceipeLayout = new CustomLayout("UserPageLayout");
                ShortViewOfReceipeLayout.setHeight("100%");
                mainLayer.contentRowLayout.setHeight("100%");
                mainLayer.contentRowLayout.addComponent(ShortViewOfReceipeLayout);
                ShortViewOfReceipeLayout.addComponent(fName, "userPageNameFieldAndLable");
        ShortViewOfReceipeLayout.addComponent(sName, "userPageSecondNameFieldAndLable");
        ShortViewOfReceipeLayout.addComponent(email, "userPageMailFieldAndLable");
                //ShortViewOfReceipeLayout.addComponent(new Label("BirthDate"), "userPageBirthDateFieldAndLable");
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
        mainLayer.contentRowLayout.removeAllComponents();
        CustomLayout ShortViewOfReceipeLayout = new CustomLayout("UserPageLayout");
        ShortViewOfReceipeLayout.setHeight("100%");
        mainLayer.contentRowLayout.setHeight("100%");
        mainLayer.contentRowLayout.addComponent(ShortViewOfReceipeLayout);

        ShortViewOfReceipeLayout.addComponent(fName, "userPageNameFieldAndLable");
        ShortViewOfReceipeLayout.addComponent(sName, "userPageSecondNameFieldAndLable");
        ShortViewOfReceipeLayout.addComponent(email, "userPageMailFieldAndLable");
        ShortViewOfReceipeLayout.addComponent(new Label("BirthDate"), "userPageBirthDateFieldAndLable");

        TextArea area = new TextArea();
        area.setValue("");
        area.setHeight("100%");
        area.setWidth("100%");
        area.setWordWrap(true);
        ShortViewOfReceipeLayout.addComponent(area, "userPageAboutOneselfFieldAndLable");

      }
    });
    
    newViews.add(new View("RecipeViewer") {
      @Override
      public void draw(LinkedMultiValueMap<String, String> parameters) {
        try{ 
            //Получение id пользователя
            CookieHandler ch2 = new CookieHandler();
            JWTHandler jwth2 = new JWTHandler();
            Cookie userCookie2 = ch2.getCookieByName("userInfo");
            String userId = jwth2.readUserId(userCookie2.getValue(), 
                    "test");
            ReceipeProxy proxy = new ReceipeProxy();
            proxy.setConfig(
                    "http://localhost:8083/", 
                    userId, parameters.
                            getFirst("receipeId"), false, false);

            ReceipeDataConverter converter = 
                    new ReceipeDataConverter();
            ReceipeStore store = new ReceipeStore(converter);

            ShowReceipeView view = new ShowReceipeView(proxy, store);
            view.reload();
            mainLayer.contentRowLayout.
                    removeAllComponents();
            mainLayer.contentRowLayout = view.
                    drawReceipe(
                    mainLayer.contentRowLayout, (form) 
                            -> {addWindow(form);
            });
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            ButtonsClickListener clickListener = new SessionStorageHelper().getListener(attr);
            clickListener.addButtonClickListener(new ClickListener() {
                @Override
                public String getId() {
                    return "ReceipeViewerParalReceipeBtn";
                }

                @Override
                public void onEventDo() {
                    try{
                        ReceipeProxy proxy = new ReceipeProxy();
                        proxy.setConfig(
                                "http://localhost:8083/", 
                                userId, parameters.
                                        getFirst("receipeId"), true, false);
                        if(!userId.equals("1")){
                            ReceipeDataConverter converter = 
                                    new ReceipeDataConverter();
                            ReceipeStore store = new ReceipeStore(converter);

                            ShowReceipeView view = new ShowReceipeView(proxy, store);
                            view.reload();
                            mainLayer.contentRowLayout.
                                    removeAllComponents();
                            mainLayer.contentRowLayout = view.
                                    drawReceipe(
                                    mainLayer.contentRowLayout, (form) 
                                            -> {addWindow(form);
                            });
                        }
                        else{
                            AuthorizationForm modalWindow = new 
                                    AuthorizationForm(UI.getCurrent());
                            addWindow(modalWindow);
                        }
                    }
                    catch(Exception exception){
                        ExceptionHandler.getInstance().
                                runExceptionhandling(exception);
                    }
                }
            });
        }
        catch(Exception exception){
            ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
      }
    });
            
    try {
      Navigator navigator = new Navigator(newViews, "Main", getPage());
      ExceptionHandler ex = ExceptionHandler.getInstance();
      ConcreteException notFoundException
              = new ConcreteException(new ConcreteExceptionHandler() {
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

      ConcreteException internalServerErrorException
              = new ConcreteException(new ConcreteExceptionHandler() {
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
          try {
            navigator.navigateTo(getUrl());
          } catch (Exception exception) {
            ExceptionHandler.getInstance().
                    runExceptionhandling(exception);
          }
        }
      });

      //navigator.load();
      navigator.navigateTo(getUrl());
    } //Следующий набор catch исправлю, сделал так (временно), чтобы здесь не 
    //обрабатывалось исключение 500 ошибки
    catch (NoViewAvailable exception) {
      ExceptionHandler.getInstance().runExceptionhandling(exception);
    } catch (InvalidQueryFormat ex) {
      ExceptionHandler.getInstance().runExceptionhandling(ex);
    } catch (NotFound ex) {
      ExceptionHandler.getInstance().runExceptionhandling(ex);
    }
    //Создаем подпункты меню
    ArrayList<MenusButton> mainSubMenus = new ArrayList<>();
    mainSubMenus.add(new MenusButton("Создать рецепт", "idsubMain1", new HandlerForClickingTheButton() {
      @Override
      public void onEventClickDo() {
        try {
            setUrl("RecipeConfigurator");
        } catch (Exception exception) {
          ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
      }
    }));

    mainSubMenus.add(new MenusButton("Просмотреть рецепт", "idsubMain2", new HandlerForClickingTheButton() {
      @Override
      public void onEventClickDo() {
        try {
            setUrl("RecipeViewer?receipeId=1");
        } catch (Exception exception) {
          ExceptionHandler.getInstance().runExceptionhandling(exception);
        }
      }
    }));
    //создаем кнопку меню, включающую подпункты
    MenusButton mainBtn = new MenusButton("Главная", "idMain", new HandlerForClickingTheButton() {
      @Override
      public void onEventClickDo() {
        setUrl("Main");
      }

    });

    //Создаем одноуровневую кнопки меню
    MenusButton recepsBtn = new MenusButton("Рецепты", "idRecept", new HandlerForClickingTheButton() {
      @Override
      public void onEventClickDo() {
        setUrl("RecipeConfigurator");
      }
    }, mainSubMenus);

    MenusSearchBar search = new MenusSearchBar("idSearch", new HandlerForClickingTheButton() {
      @Override
      public void onEventClickDo() {
        setUrl("Search");
      }
    });

    try {
      CookieHandler ch = new CookieHandler();
      JWTHandler jwth = new JWTHandler();
      Cookie userCookie = ch.getCookieByName("userInfo");
      if (userCookie == null) {
        MenusButton registration = new MenusButton("Регистрация", "idregistration", new HandlerForClickingTheButton() {
          @Override
          public void onEventClickDo() {
            RegistrationForm modalWindow = new RegistrationForm(UI.getCurrent());
            addWindow(modalWindow);
          }
        });
        MenusButton signIn = new MenusButton("Войти", "idSignin", new HandlerForClickingTheButton() {
          @Override
          public void onEventClickDo() {
            AuthorizationForm modalWindow = new AuthorizationForm(UI.getCurrent());
            addWindow(modalWindow);
          }
        });
        mainLayer.menu.addItem(mainBtn);
        mainLayer.menu.addItem(recepsBtn);
        mainLayer.menu.addItem(search);
        mainLayer.menu.addItem(signIn);
        mainLayer.menu.addItem(registration);
      } else {
        boolean user = jwth.checkUser(userCookie.getValue(), "test");
        if (user == false) {
          MenusButton registration = new MenusButton("Регистрация", "idregistration", new HandlerForClickingTheButton() {
            @Override
            public void onEventClickDo() {
              RegistrationForm modalWindow = new RegistrationForm(UI.getCurrent());
              addWindow(modalWindow);
            }
          });
          MenusButton signIn = new MenusButton("Войти", "idSignin", new HandlerForClickingTheButton() {
            @Override
            public void onEventClickDo() {
              AuthorizationForm modalWindow = new AuthorizationForm(UI.getCurrent());
              addWindow(modalWindow);
            }
          });
          mainLayer.menu.addItem(mainBtn);
          mainLayer.menu.addItem(recepsBtn);
          mainLayer.menu.addItem(search);
          mainLayer.menu.addItem(signIn);
          mainLayer.menu.addItem(registration);
        } else {
          MenusButton userPageBtn = new MenusButton("Профиль", "iduserPage", new HandlerForClickingTheButton() {
            @Override
            public void onEventClickDo() {
              try {
                getPage().setUriFragment("UserPage");
//                BeansFactory<Properties> bfP = BeansFactory.getInstance();
//                Properties p = bfP.getBean(Properties.class);
//                BeansFactory<RestTemplate> bfOM = BeansFactory.getInstance();
//                RestTemplate restTemplate = bfOM.getBean(RestTemplate.class);
//                UserDto dto =  restTemplate.getForObject("http://"+p.getIdpURL()+"/idpsecure/getUserData", UserDto.class);
//                System.out.println(dto);
////                getPage().setUriFragment("UserPage");
////                
////                BeansFactory<SecurityTokenHandler> bfSTH = BeansFactory.getInstance();
////                SecurityTokenHandler tokenStore = bfSTH.getBean(SecurityTokenHandler.class);
////                UserDto userInfo = new UserDto();
////                CookieHandler ch = new CookieHandler();
////
////                userInfo.setId(new JWTHandler().readUserId(ch.getCookieByName("userInfo").getValue(), "test"));
////                PostUserData post = new PostUserData("http://"+p.getIdpURL()+"/idpsecure/getUserData", userInfo, tokenStore.getToken());
////                int response = post.con.getResponseCode();
              } catch (Exception ex) {
                java.util.logging.Logger.getLogger(UiServiceMainUI.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
          });

          MenusButton exitBtn = new MenusButton("Выход", "idExit", new HandlerForClickingTheButton() {
            @Override
            public void onEventClickDo() {
              CookieHandler ch = new CookieHandler();
              ch.getCookieForGuest();
              new ExitForm(UI.getCurrent());
            }
          });

          mainLayer.menu.addItem(mainBtn);
          mainLayer.menu.addItem(recepsBtn);
          mainLayer.menu.addItem(search);
          mainLayer.menu.addItem(userPageBtn);
          mainLayer.menu.addItem(exitBtn);
        }
      }
    } catch (Exception exception) {
      ExceptionHandler.getInstance().runExceptionhandling(exception);
    }

    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    ButtonsClickListener clickListener = new SessionStorageHelper().getListener(attr);

    //Артем, назначь действия на событие onClick для следующих кнопок:
    clickListener.addButtonClickListener(new ClickListener() {
      @Override
      public String getId() {
        return "userPageSaveBtn";
      }

      @Override
      public void onEventDo() {
        try {
          System.out.println("Buuton");
          BeansFactory<Properties> bfP = BeansFactory.getInstance();
          Properties p = bfP.getBean(Properties.class);
          BeansFactory<SecurityTokenHandler> bfSTH = BeansFactory.getInstance();
          SecurityTokenHandler tokenStore = bfSTH.getBean(SecurityTokenHandler.class);
          UserDto userInfo = new UserDto();
          CookieHandler ch = new CookieHandler();


          userInfo.setName(fName.getValue());
          userInfo.setLastname(sName.getValue());
          userInfo.setEmail(email.getValue());
          userInfo.setId(new JWTHandler().readUserId(ch.getCookieByName("userInfo").getValue(), "test"));
          PostUserData post = new PostUserData("http://" + p.getIdpURL() + "/idpsecure/saveUserData", userInfo, tokenStore.getToken());
          int response = post.con.getResponseCode();

          switch (response) {
            case 200:
              Notification n = new Notification("Данные успешно сохранены");
              n.setDelayMsec(2000);
              n.show(Page.getCurrent());
              break;
            case 415:
              Notification q = new Notification("Вы ввели неверные данные");
              q.setDelayMsec(2000);
              q.show(Page.getCurrent());
              break;
          }

        } catch (Exception ex) {
          java.util.logging.Logger.getLogger(UiServiceMainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
    clickListener.addButtonClickListener(new ClickListener() {
      @Override
      public String getId() {
        return "userPageCancelBtn";
      }

      @Override
      public void onEventDo() {
        int i = 0;//Код писать сюда
      }
    });
    clickListener.addButtonClickListener(new ClickListener() {
      @Override
      public String getId() {
        return "userPageLoadFotoBtn";
      }

      @Override
      public void onEventDo() {
        
      }
    });
    return mainLayer.contentRowLayout;
  }

  private void addSliderComponent(ResponsiveLayout contentRowLayout) {
    //Создание строки, для добавления конкретного контента на даную страницу
    ResponsiveRow sliderRow = contentRowLayout.addRow();
    //Создание custom слоя для добавления слайдера
    CustomLayout sliderLayout = new CustomLayout("SliderLayout");
    sliderRow.addColumn().withDisplayRules(12, 12, 12, 12).withComponent(sliderLayout);
  }

  private void addTopRecepiesComponent(ResponsiveLayout contentRowLayout) throws NotFoundBean {
    //Отрисовка заголовка топа рецептов
    ResponsiveRow recipeTitle = contentRowLayout.addRow();
    CustomLayout topRecipeTitleLayout = new CustomLayout("TopRecipeTitle");
    recipeTitle.addColumn().withDisplayRules(12, 12, 12, 12).withComponent(topRecipeTitleLayout);
    //Здесь можно разместить добавление рецептов либо фиксированно, например, топ 5, или
    //Задать количество по какому-либо другому параметру, например, по нажатию кнопки добавлять
    //еще несколько к имеющемуся списку
    for (int i = 0; i < 8; i++) {
      BeansFactory<ContentManagerController> bfCMC = BeansFactory.getInstance();
      ContentManagerController controller = bfCMC.getBean(ContentManagerController.class);
      //Задание отступа между рецептами
      ResponsiveRow theDistanceBetweenRecipe = contentRowLayout.addRow();
      theDistanceBetweenRecipe.setHeight("30px");
      theDistanceBetweenRecipe.addColumn().withDisplayRules(12, 12, 12, 12);
      //Отрисовка изображения рецепта
      ResponsiveRow recipeRow = contentRowLayout.addRow();
      Image topImage = new Image();
      topImage.setSource(new ExternalResource(controller.getImage("cake")));
      topImage.setHeight("70%");
      topImage.setWidth("100%");
      recipeRow.addColumn().withDisplayRules(2, 2, 2, 2).withComponent(topImage);
      //Создание custom макета с поддержкой flexbox
      CustomLayout topRecipeLayout = new CustomLayout("TopRecipeLayout");
      recipeRow.addColumn().withDisplayRules(8, 8, 8, 8).withComponent(topRecipeLayout);

      //Задание информации по каждому из рецептов
      Label recipesName = new Label("Название");
      topRecipeLayout.addComponent(recipesName, "recipes_name");
      Label recipesAuthor = new Label("Автор");
      topRecipeLayout.addComponent(recipesAuthor, "recipes_author");
      Button recepiesPartsButton = new Button("Ингридиенты");
      topRecipeLayout.addComponent(recepiesPartsButton, "parts_recipe_button");
      Label numberOfServingsLable = new Label(String.valueOf(i));//просто для примера
      topRecipeLayout.addComponent(numberOfServingsLable, "number_of_servings_lable");
      Label workingTimesLable = new Label(String.valueOf(i));//просто для примера
      topRecipeLayout.addComponent(workingTimesLable, "working_times_lable");
      Button addRecipeToFavoritesButton = new Button("Добавить в избранное");
      topRecipeLayout.addComponent(addRecipeToFavoritesButton, "add_recipe_to_favorites_button");
    }

  }

    //Функция проверки наличия незавершенного рецепта пользователем
    private String checkNonFinishRecipe(String userId){
        try{
            BeansFactory<GMFacade> bf = BeansFactory.getInstance();
            GMFacade gmFacade = bf.getBean(GMFacade.class);
            return gmFacade.getGmGrafFacade().getNotCompletedGraph(userId);
        }
        catch(Exception exception){
            return "";
        }

    }
}


