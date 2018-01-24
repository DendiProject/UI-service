// Define the namespace
var mylibrary = mylibrary || {};

mylibrary.MyComponent = function (element) {
        //Считывание id нажатой кнопки
        this.getValue = function () 
        {
            //Вернем "чистый id кнопки, то есть без идентификатора Btn"
            var clean = getCookie('idClickedButton');
            return clean.slice(0,(clean.length-3));
        };

        var self = this; // Can't use this inside the function
        var button =document.getElementById("left_allign_navigate_row_components"); 
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
                    document.cookie = 'idClickedButton=' + action;
                    self.click();
                }
            }
        };
        
        // возвращает cookie с именем name, если есть, если нет, то undefined
        function getCookie(name) 
        {
            var matches = document.cookie.match(new RegExp(
              "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
            ));
            return matches ? decodeURIComponent(matches[1]) : undefined;
        }
};