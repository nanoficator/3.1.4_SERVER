$(document).on('click', '#test', function () {
    $.ajax({
        url: '/test',
        type: 'get',
        success: function () {
            alert('2');
        }
    })
});