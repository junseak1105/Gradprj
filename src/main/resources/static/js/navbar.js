var page_category;
var page_list;

$().ready(function () {
    get_page_category();
    get_page_list();
    set_navdata();
});

function get_page_category(){
    $.ajax({
        type: "GET",
        url: "/api/navbar/pageCategory",
        dataType: 'json',
        contentType: 'application/json',
        async:false,
        success: function (data) {
            // console.log("Page_Category | StatusCode:"+data.statusCode+"ResponseMessage:"+data.responseMessage);
            page_category=data.data;
        }
    });
}
function get_page_list(){
    $.ajax({
        type: "GET",
        url: "/api/navbar/pageList",
        dataType: 'json',
        contentType: 'application/json',
        async:false,
        success: function (data) {
            // console.log("Page_List | StatusCode:"+data.statusCode+"ResponseMessage:"+data.responseMessage);
            page_list=data.data;
        }
    });
}

function set_navdata(){
    for(var i=0; i<page_category.length; i++){
        category = page_category[i].category_name;

        $li=$("<li></li>");
        $li.append("<a href=\"#\"><i class=\"fa fa-bar-chart-o fa-fw\"></i> "+category+"<span class=\"fa arrow\"></span></a>");
        $ul=$("<ul class=\"nav nav-second-level\"></ul>");
        for(var j=0; j<page_list.length; j++){
            pagename = page_list[j].page_name;
            page_code = page_list[j].page_code;
            if(page_list[j].category_name==category){
                $ul.append("<li><a href=\"javascript:void(0);\" onclick=\"set_content('/list/"+page_code+"')\">"+pagename+"</a> </li>");
            }
        }
        $li.append($ul);
        $("#side-menu").append($li);
    }
}