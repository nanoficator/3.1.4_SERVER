$('#addUserBtn').on('click', function () {

    let formdata = $("#addUserForm").serializeArray();
    let user = {};
    let authorities = {};
    $(formdata).each(function(index, obj){
        user[obj.name] = obj.value;
    });

    $.ajax({
        url: '/admin/add-user',
        type: 'post',
        dataType: 'json',
        data: user,
        contentType: 'application/json',
        success: function () {
            alert('success');
        },
        error: function () {
            alert('error');
        }
    });
});