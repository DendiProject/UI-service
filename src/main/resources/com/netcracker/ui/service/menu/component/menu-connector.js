window.com_netcracker_ui_service_menu_component_EventHandlerOfTheMenu = function() 
{
    //В конструктор отправляем текущий DOM
    var mycomponent = new mylibrary.MyComponent(this.getElement());

    this.onStateChange = function() 
    {
        //Установка высоты блока с контентом. Требуется высота в пикселях, так
        //как блок вложен и нет возможности использовать проценты
        var scr_h=screen.height*0.80;
        var scr_h = scr_h+32;
        document.getElementById("content_row").style.height=scr_h+"px";
    };

    // Pass user interaction to the server-side
    var self = this;
    mycomponent.click = function() 
    {
        self.onClick(mycomponent.getValue());
    };
};