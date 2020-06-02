$('#user-modal').on('show.bs.modal', function () {
    let user = $('#user-modal').data('bs.modal')._config.user;
    $('#user-modal .modal-title').text('Delete User');
    $('#user-id')
        .attr({
            'value': user.id,
            'disabled': true,
        });
    $('#user-username')
        .attr({
            'value': user.username,
            'disabled': true,
        });
    $('#user-password')
        .attr({
            'disabled': true,
        });
    $('#user-confirmPassword')
        .attr({
            'disabled': true,
        });
    $('#user-authorities')
        .attr({
            'disabled': true,
        });

    // $('#user-authorities')
    //     .append(
    //         $(user.authorities).each(function (i, authority) {
    //             return $('<option>').text(authority.name.substring(5));
    //         })
    //     )
    //     .attr({
    //         'disabled': true
    //     });
    $('#user-accountNonExpired')
        .attr({
            'checked': user.accountNonExpired,
            'disabled': true,
        });
    $('#user-accountNonLocked')
        .attr({
            'checked': user.accountNonLocked,
            'disabled': true,
        });
    $('#user-credentialsNonExpired')
        .attr({
            'checked': user.credentialsNonExpired,
            'disabled': true,
        });
    $('#user-enabled')
        .attr({
            'checked': user.enabled,
            'disabled': true,
        });
});