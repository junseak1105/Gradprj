$().ready(function() {

});


//ajax get pagelist
function getPageList() {
    $.ajax({
        url: "/admin/service/simplex/get_page_list",
        type: "get",
        dataType: "json",
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                html += '<li><a href="' + data[i].url + '">' + data[i].title + '</a></li>';
            }
            $("#pageList").html(html);
        }
    });
}