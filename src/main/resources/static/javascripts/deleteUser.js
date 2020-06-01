$('#delete-user-modal').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget);
    let user = button.data;
});