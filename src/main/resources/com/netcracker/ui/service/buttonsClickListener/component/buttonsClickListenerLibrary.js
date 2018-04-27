// Define the namespace
var mylibrary = mylibrary || {};

mylibrary.MyButtonsClickListener = function (element) {
        var idBtn;
        //Считывание id нажатой кнопки
        this.getValue = function () 
        {
            //Вернем "чистый id кнопки, то есть без идентификатора Btn"
            return idBtn;
        };

        var self = this; // Can't use this inside the function
        var button =document.getElementById("content_row"); 
        button.onclick = function(event) 
        {
            var target = event.target; // где был клик?
            var action = target.getAttribute('id');
            //Кнопка, если последние 3 символа - Btn
            //Для начала проверяем длинну, чтобы не вылетало исключение
            if(action !== null && action.length>4)//название хотя бы 1 символ, 3 символа на Btn
            {
                //В конце каждого id кнопки должны стоять символы Btn
                var chekBtnSymbols = action.substr((action.length-3), 3);
                if(chekBtnSymbols === 'Btn')//Если true, то вызываем событие
                {     
                    //document.cookie = 'idClickedButton=' + action;
                    idBtn = action;
                    self.click();
                }
            }
        };
};