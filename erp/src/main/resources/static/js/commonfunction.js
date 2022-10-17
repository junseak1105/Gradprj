form_to_json($("#fr_pagelist").serializeArray())
function form_to_json(form) {
    var json = {};
    $.each(form, function () {
        if (json[this.name]) {
            if (!json[this.name].push) {
                json[this.name] = [json[this.name]];
            }
            json[this.name].push(this.value || '');
        } else {
            json[this.name] = this.value || '';
        }
    });
    console.log(json);
    return JSON.stringify(json);
}

function show_content(value){
    $.ajax({
        url: value,
        type: "GET",
        success: function (data) {
            $("#page-wrapper").html(data);
        }
    });
}