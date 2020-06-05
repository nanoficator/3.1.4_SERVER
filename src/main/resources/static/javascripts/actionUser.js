$('#user-form').submit(
    function (event) {
        event.preventDefault();
        let user = serializeFormToUser($(this));
        let action = this.dataset.action;
        let message = (action == 'Delete' ? deleteUser(user) : editUser(user))
        console.log(message);
        $('#modal-window').modal('hide');
        $('#users-table-tab-btn').trigger('click');
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