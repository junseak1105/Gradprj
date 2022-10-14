$().ready(function () {//시작시 세팅
    makeSideBar();
});

function makeSideBar(){
    getPageList();
    getPageCategory();

}
//ajax get pageCategory
function getPageCategory() {
    $.ajax({
        url: "/api/page/getPageCategory",
        type: "get",
        dataType: "json",
        success: function (data) {
            makeSideBarCategory(data);
        }
    });
}

//ajax get pagelist
function getPageList() {
    $.ajax({
        url: "/api/page/getPageList",
        type: "get",
        dataType: "json",
        success: function (data) {
            makeSideBarList(data);
        }
    });
}

function makeSideBarCategory(data) {
    for(var i=0; i<data.length; i++) {
        var category = data[i].page_category;
        $li = $("<li></li>").attr("class", "active");
        $a = $("<a></a>").attr("href", "#"+category).attr("data-toggle", "collapse").attr("data-target", "#"+category).attr("class","dropdown-toggle").attr("aria-expanded", "false").text(category);
        $ul = $("<ul></ul>").attr("class", "collapse list-unstyled").attr("id", category);
        $("#sidebar_content").append($li.append($a.text(category)).append($ul));
    }
}

function makeSideBarList(data) {
    for(var i=0; i<data.length; i++) {
        var page_desc = data[i].page_desc;
        var page_name = data[i].page_name;
        var page_category = data[i].page_category;
        $li = $("<li></li>");
        $a = $("<a></a>").attr("href", "/datagrid/"+page_name).text(page_desc);
        $li.append($a);
        $("#"+page_category).append($li);
    }
}