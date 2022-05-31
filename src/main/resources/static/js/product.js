$(function() {
    $('select').on('change', function() {
        console.info(this.value,
            this.options[this.selectedIndex].value,
            $(this).find("option:selected").val());
    });
});