$.namespace('pharmacon');
pharmacon.mainController = (function() {
    return {
        getPage: function () {
            $.get("hello/getMainPage",function(data,status){
                var body = $("body");
                pharmacon.ComponentBuilder.buildComponent(body ,data.content);

            });
        }
    };
})();
