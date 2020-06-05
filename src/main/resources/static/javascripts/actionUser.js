$('#user-form').submit(
    function (event) {
        event.preventDefault();
        let user = serializeFormToUser($(this));
        let action = this.dataset.action;
        if (action == 'Delete') {
            deleteUser(user);
        } else if (action == 'Edit') {
            editUser(user);
        }
        $('#modal-window').modal('hide');
    }
)

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
    user.authorities = authorities;

    return user;
}