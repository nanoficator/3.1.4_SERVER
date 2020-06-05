$('#user-form').submit(
    function (event) {
        event.preventDefault();
        let user = serializeFormToUser($(this));
        let action = '';
        if (action == 'delete') {
            deleteUser(user);
        } else if (action == 'edit') {
            editUser(user);
        }
        $('#modal-window').modal('hide');
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

function serializeFormToUser(form) {
    $(form).find(':disabled').removeAttr('disabled')
    let formData = form.serializeArray();
    let user = {};
    $(formData).each(
        (index, obj) => {
            user[obj.name] = obj.value;
        }
    );
    $(form).find(':checkbox').each(
        () => {(
            this.checked ? user[this.name] = true : user[this.name] = false
        )}
    );
    return user;
}