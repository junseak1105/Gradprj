//현재 컨텐츠 경로
content_url="/dashboard";


$(document).ready(function () {
    show_content();
});
function set_content(value){
    content_url=value;
    show_content();
}
function show_content(){
    $.ajax({
        url: content_url,
        type: "GET",
        success: function (data) {
            $("#page-wrapper").html(data);
        }
    });
}

