$('#add-new-user-tab-btn').on('click', function () {
    buildCardNewUser();
})

function buildCardNewUser() {
    $('#add-new-user-card').append($('#user-form'))
    let user = {};
    user.id = null;
    user.username = null;
    user.password = null;
    user.confirmPassword = null;
    buildFormUser(user, 'Add');
}