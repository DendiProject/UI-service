window.com_netcracker_ui_service_menu_component_EventHandlerOfTheForm = function() 
{
    //Создание компонента, в конструктор отправляем текущий DOM
    var mycomponent = new mylibrary.MyComponent(this.getElement());

    this.onStateChange = function() 
    {
        //Изменение ширины главного слоя, статически нельзя задать через проценты(не работает), а нужно задать 85 процентов
        //Позже перенесу в другой компонент
        var scr_w=screen.width*0.85;
        document.getElementById("main_layout").style.width=scr_w+"px";
    };

    // Pass user interaction to the server-side
    var self = this;
    mycomponent.click = function() 
    {
        //Считывание значения из первого элемента с тегом "input"
        self.onClick(mycomponent.getValue());
    };
};