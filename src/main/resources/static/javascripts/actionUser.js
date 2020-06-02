$('#user-form').submit(
    function (event) {
        event.preventDefault();
        let formData = $("#user-form").serializeArray();
        let user = {};
        $(formData).each(function (index, obj) {
            user[obj.name] = obj.value;
        });
        let action;
        if (action == 'delete') {
            deleteUser(user);
        } else if (action == 'edit') {
            editUser(user);
        }
        $('#user-modal').modal('hide');
    }
)

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

function editUser(user) {
    $.ajax({
        url: '/admin/edit-user',
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