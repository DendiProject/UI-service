window.com_netcracker_ui_service_buttonsClickListener_component_ButtonsClickListener = function() 
{
    //В конструктор отправляем текущий DOM
    var myButtonsClickListener = new mylibrary.MyButtonsClickListener(this.getElement());

    this.onStateChange = function() 
    {
        //Установка высоты блока с контентом. Требуется высота в пикселях, так
        //как блок вложен и нет возможности использовать проценты
        //var scr_h=screen.height*0.75;
        //document.getElementById("content_row").style.height=scr_h+"px";
    };

    // Pass user interaction to the server-side
    var self = this;
    myButtonsClickListener.click = function() 
    {
        self.onClick(myButtonsClickListener.getValue());
    };
};