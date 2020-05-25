$('#test').bind('click', function(){
    alert('1');
    $.ajax({
        url: '/test',
        type: 'get',
        success: function () {
            alert('Success');
        },
        error: function () {
            alert('Error');
        }
    });
});
