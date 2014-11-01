$.namespace('pharmacon.factory');
pharmacon.factory.TileFactory = (function() {

    return {

        buildTile: function (tileConfig) {
            var tile = $(document.createElement('div'));
            var tileHeader = tileConfig.header;
            var tileContentConfig = tileConfig.tileContent.content;
            pharmacon.ComponentBuilder.buildComponent(tile, tileHeader);
            pharmacon.ComponentBuilder.buildComponent(tile, tileContentConfig);
            tile.addClass('tile triple double vertical bg-blue fg-white');
            return tile;
        }


    };
})();