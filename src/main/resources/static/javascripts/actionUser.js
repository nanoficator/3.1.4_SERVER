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
        function () {(
            user[this.name] = (this.checked ? true : false)
        )}
    );

    let authorities = [];
    $('#user-authorities').find(':selected').each(
        (i, option) => {
            authorities[i] = {
                id: option.dataset.id,
                name: option.dataset.name,
                authority: option.dataset.authority
            };
        }
    );
    user['authorities'] = authorities;
    return user;

}