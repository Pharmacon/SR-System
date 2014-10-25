var tileFactory = (function() {

    return {

        buildTile: function (tileConfig) {
            var tile = $(document.createElement('div'));
            var tileHeader = tileConfig.header;
            var tileContentConfig = tileConfig.tileContent.content;
            componentBuilder.buildComponent(tile, tileHeader);
            componentBuilder.buildComponent(tile, tileContentConfig);
            tile.addClass('tile triple double vertical bg-blue fg-white');
            return tile;
        }


    };
})();