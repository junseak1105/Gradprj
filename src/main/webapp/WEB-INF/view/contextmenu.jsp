<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@include file="includes/injection.jsp" %>
<style>
    * {
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }


    .contextmenu {
        display: none;
        position: absolute;
        width: 200px;
        margin: 0;
        padding: 0;
        background: #FFFFFF;
        border-radius: 5px;
        list-style: none;
        box-shadow:
                0 15px 35px rgba(50,50,90,0.1),
                0 5px 15px rgba(0,0,0,0.07);
        overflow: hidden;
        z-index: 999999;
    }

    .contextmenu li {
        border-left: 3px solid transparent;
        transition: ease .2s;
    }

    .contextmenu li a {
        display: block;
        padding: 10px;
        color: #B0BEC5;
        text-decoration: none;
        transition: ease .2s;
    }

    .contextmenu li:hover {
        background: #CE93D8;
        border-left: 3px solid #9C27B0;
    }

    .contextmenu li:hover a {
        color: #FFFFFF;
    }
</style>

<ul class="contextmenu">
<%--    <li><a href="#">Item 1</a></li>--%>
<%--    <li><a href="#">Item 2</a></li>--%>
<%--    <li><a href="#">Item 3</a></li>--%>
<%--    <li><a href="#">Item 4</a></li>--%>
<%--    <li><a href="#">Item 5</a></li>--%>
</ul>

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">

<script>
    $(document).ready(function(){
        //Show contextmenu:
        // $(document).contextmenu(function(e){
        //     //Get window size:
        //     var winWidth = $(document).width();
        //     var winHeight = $(document).height();
        //     //Get pointer position:
        //     var posX = e.pageX;
        //     var posY = e.pageY;
        //     //Get contextmenu size:
        //     var menuWidth = $(".contextmenu").width();
        //     var menuHeight = $(".contextmenu").height();
        //     //Security margin:
        //     var secMargin = 10;
        //     //Prevent page overflow:
        //     if(posX + menuWidth + secMargin >= winWidth
        //         && posY + menuHeight + secMargin >= winHeight){
        //         //Case 1: right-bottom overflow:
        //         posLeft = posX - menuWidth - secMargin + "px";
        //         posTop = posY - menuHeight - secMargin + "px";
        //     }
        //     else if(posX + menuWidth + secMargin >= winWidth){
        //         //Case 2: right overflow:
        //         posLeft = posX - menuWidth - secMargin + "px";
        //         posTop = posY + secMargin + "px";
        //     }
        //     else if(posY + menuHeight + secMargin >= winHeight){
        //         //Case 3: bottom overflow:
        //         posLeft = posX + secMargin + "px";
        //         posTop = posY - menuHeight - secMargin + "px";
        //     }
        //     else {
        //         //Case 4: default values:
        //         posLeft = posX + secMargin + "px";
        //         posTop = posY + secMargin + "px";
        //     };
        //     //Display contextmenu:
        //     $(".contextmenu").css({
        //         "left": posLeft,
        //         "top": posTop
        //     }).show();
        //     //Prevent browser default contextmenu.
        //     return false;
        // });
        // //Hide contextmenu:
        // $(document).click(function(){
        //     $(".contextmenu").hide();
        // });
    });
</script>