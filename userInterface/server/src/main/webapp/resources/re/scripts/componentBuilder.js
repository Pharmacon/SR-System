$.namespace('pharmacon');
pharmacon.ComponentBuilder = (function() {

    createCmp = function (componentType, config) {
        var newComponent;
        switch(componentType){
            case 'tile' :{
                newComponent = pharmacon.factory.TileFactory.buildTile(config);
                break;
            }
            case 'text':{
                newComponent = pharmacon.factory.TextFactory.buildText(config);
                break;
            }
            default:
        }

        return newComponent;
    };

    buildMultipleComponents = function (parentElement, config) {
        var i;
        var componentType;
        var newComponent;
        for(i = 0; i < config.length; ++i){
            componentType = config[i].componentType;
            newComponent = createCmp(componentType, config[i]);
            parentElement.append(newComponent);
        }
    };

    buildSingleComponent = function (parentElement, config) {
        var componentType = config.componentType;
        var newComponent = createCmp(componentType, config);
        parentElement.append(newComponent);
    };

    return {

        buildComponent: function (parentElement, config) {
            if(config.length){
                buildMultipleComponents(parentElement, config);
            }
            else{
                buildSingleComponent(parentElement, config);
            }

        }
    };
})();