$('#add-user-form').submit(function () {
    $.ajax({
        url: '/admin/add-user',
        type: 'post',
        data: $('#add-user-form').serialize(),
        success: function () {
            alert('1');
        }
    });
});