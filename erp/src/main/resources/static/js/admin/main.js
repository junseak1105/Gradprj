$(document).ready(function () {
});
function show_content(value){
    $.ajax({
        url: value,
        type: "GET",
        success: function (data) {
            $("#content").html(data);
        }
    });
}