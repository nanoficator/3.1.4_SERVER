$('#modal-window').on('show.bs.modal', (event) => {
    buildModalUser(
        $(event.relatedTarget).data('user'),
        $(event.relatedTarget).data('action')
    );
});

function buildModalUser(user, action) {
    $('#modal-window .modal-title').text(action + ' user');
    $('#modal-window .modal-body').append($('#user-form'));
    buildFormUser(user, action);
}