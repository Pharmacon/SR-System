var mainController = (function() {
    return {
        getPage: function () {
            $.get("hello/getMainPage",function(data,status){
                var body = $("body");
                componentBuilder.buildComponent(body ,data.content);

            });
        }
    };
})();
