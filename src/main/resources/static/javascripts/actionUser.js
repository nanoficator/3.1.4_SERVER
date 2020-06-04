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
    let user = {};
    $(form).find(':checkbox').each(
        function () {
            (this.checked ? user[this.name] = true : user[this.name] = false)
        }
    )
    $(form).find(':text').each(
        function() {
            user[this.name] = this.value;
        }
    )
    $(form).find(':password').each(
        function() {
            user[this.name] = this.value;
        }
    )
    return user;
}