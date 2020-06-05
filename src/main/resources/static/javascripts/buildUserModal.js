$('#modal-window').on('show.bs.modal', function (event) {
    let user = $(event.relatedTarget).data('user');
    let action = $(event.relatedTarget).data('action');
    buildModalUser(user, action);
});

function buildModalUser(user, action) {

    let disabledForm = (action == 'Delete' ? true : false);

    $('#modal-window .modal-title').text(action + ' user');
    $('#modal-window .modal-body')
        .append(
            $('#user-form')
                .attr({
                    'hidden' : false,
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
            'disabled': disabledForm,
        });
    $('#user-password')
        .attr({
            'disabled': disabledForm,
        })
        .removeAttr('placeholder');
    $('#user-confirmPassword')
        .attr({
            'disabled': disabledForm,
        })
        .removeAttr('placeholder');
    $('#user-authorities')
        .attr({
            'disabled': disabledForm,
        })
    $('#user-authorities').html('');
    let allAuthorities = getAllAuthorities();
    $(allAuthorities).each(
        function (i, authority) {
            let userHasAuthority = false;
            $(user.authorities).each(
                function (j, userAuthority) {
                    if (userAuthority.name == authority.name && userAuthority.id == authority.id) {
                        userHasAuthority = true;
                    }
                }
            )
            $('#user-authorities').append(
                $('<option>')
                    .text(authority.name.substring(5))
                    .attr({
                        'value' : authority.name,
                        'selected' : userHasAuthority
                    })
            )
        }
    )
    $('#user-accountNonExpired')
        .attr({
            'checked': user.accountNonExpired,
            'disabled': disabledForm,
        });
    $('#user-accountNonLocked')
        .attr({
            'checked': user.accountNonLocked,
            'disabled': disabledForm,
        });
    $('#user-credentialsNonExpired')
        .attr({
            'checked': user.credentialsNonExpired,
            'disabled': disabledForm,
        });
    $('#user-enabled')
        .attr({
            'checked': user.enabled,
            'disabled': disabledForm,
        });
    $('#user-form :submit')
        .text('Delete')
        .attr({
            'class': (disabledForm ? 'btn btn-danger' : 'btn btn-info'),
            'name' : action
        })

}