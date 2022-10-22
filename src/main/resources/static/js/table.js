/*Get information all cells*/

$(document).keyup(function () {
    var tdRows = $('#inputTd').val();
    var trColl = $('#inputTr').val();
    var tdRows = $('#inputTd').val();
});

$(document).ready(function () {

    $('#btn').click(function () {

        $('#tablesSource').empty();
        $('#tablesCode').empty();

        $('#tablesResult').show();

        var trColl = $('#inputTr').val();
        var tdRows = $('#inputTd').val();

        $('#tablesSource').append('&lt;table&gt;');
        $('#tablesCode').append('<table>');

        for (a = 1; a <= tdRows; a++) {

            $('#tablesSource').append('&lt;tr&gt;');
            $('#tablesCode').append('<tr>');

            for (b = 1; b <= trColl; b++) {

                $('#tablesSource').append('&lt;td&gt;' + b + '\) result' + '&lt;/td&gt;');
                $('#tablesCode').append('<td>' + b + '\) result ' + '</td>');
            }

            $('#tablesSource').append('&lt;/tr&gt;');
            $('#tablesCode').append('</tr>');
        }

        $('#tablesSource').append('&lt;/table&gt;');
        $('#tablesCode').append('</table>');

    });
});
