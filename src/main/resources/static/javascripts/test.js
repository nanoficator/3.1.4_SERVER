$('#test').on('click', function () {
    $.ajax({
        url: '/test',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            alert('Done!');
            $('#test-content').html(data);
        }
    });
});