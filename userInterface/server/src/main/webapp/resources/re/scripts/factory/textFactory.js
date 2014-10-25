var textFactory = (function() {

    return {

        buildText: function (textConfig) {
            var textComponent = $(document.createElement('p'));
            textComponent.addClass('fg-white');
            var innerText = textConfig.innerText;
            textComponent.append(innerText);
            return textComponent;
        }


    };
})();
