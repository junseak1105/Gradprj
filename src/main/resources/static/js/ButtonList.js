const ButtonList = new Map();
ButtonList.set("copyBtn",{
    extend: 'copy',
    text: 'Copy',
    className: 'btn btn-default'
});



export function hello(name){
    console.log(`hello, world! I'm ${name}!`);
}



//엑셀 출력 버튼
var excelBtn = {
    extend: 'excel', //파일명
    title: 'excel',
    className: 'btn btn-success',
};

var printBtn = {
    extend: 'print',
    className: 'btn btn-info',
    title: '품번출력',
    customize: function (win) {
        $(win.document.body)
            .css('font-size', '10pt')
            .prepend(
                '<img src="http://datatables.net/media/images/logo-fade.png" style="position:absolute; top:0; left:0;" />'
            );
        $(win.document.body).find('table')
            .addClass('compact')
            .css('font-size', 'inherit');
        $(win.document.body).find('h1').remove();
        let $header;
        $header = $('<table border="1px solid black" style="width: 100%">' +
            '<tr>' +
            '<td>이름</td>' +
            '<td>이메일</td>' +
            '<td>전화번호</td>' +
            '</tr>' +
            '<tr>' +
            '<td>홍길동</td>' +
            '<td>email</td>' +
            '<td>010-1234-5678</td>' +
            '</tr>' +
            '</table>')
        let $footer;
        $footer = $('<table border="1px solid black" style="width: 100%" >' +
            '<tr>' +
            '<td>이름</td>' +
            '<td>이메일</td>' +
            '<td>전화번호</td>' +
            '</tr>' +
            '</table>');
        $(win.document.body).find('table').prev().append($header);
        $(win.document.body).append($footer);
    }
};
export default class Buttons{
    constructor(){
        this.buttons = [copyBtn, excelBtn, printBtn];
    }
    getButtons(){
        return this.buttons;
    }
}

