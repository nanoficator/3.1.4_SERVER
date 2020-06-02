$('#modal-window').on('show.bs.modal', function (event) {
    let user = $(event.relatedTarget).data('user');
    let action = $(event.relatedTarget).data('action');
    if (action == 'delete') {
        deleteUserModal(user);
    } else if (action == 'edit') {
        editUserModal(user);
    }
});

function deleteUserModal(user) {
    $('#modal-window .modal-title').text('Delete User');
    $('#modal-window .modal-body')
        .append(
            $('#user-form')
                .attr({
                    'hidden' : false
                })
        );
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
        })
        $('#user-authorities').html('');
        $(user.authorities).each(function (i, authority) {
                $('#user-authorities').append(
                    $('<option>')
                        .text(authority.name.substring(5))
                        .attr({
                            'value' : authority
                        })
                )
            })
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
    $('#user-form :submit')
        .text('Delete')
        .attr({
            'class': 'btn btn-danger',
            'name' : 'delete'
        })
}