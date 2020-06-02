$('#modal-button').on('click', function (event) {
    let action = event.target.attributes.name.nodeValue;
    let formData = $("#user-form").serializeArray();
    let user = {};
    $(formData).each(function(index, obj){
        user[obj.name] = obj.value;
    });
    if (action == 'delete') {
        deleteUser(user);
    } else if (action == 'edit') {
        editUser(user);
    }
})

function deleteUser(user) {
    $.ajax({
        url: '/admin/delete-user',
        data: user,
        type: 'post',
        success: function (data) {
            alert(data);
        },
        error: function () {
            alert('error');
        }
    })
}