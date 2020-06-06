function buildFormUser(user, action) {
    let actionIsDelete = (action == 'Delete' ? true : false);
    let actionIsAdd = (action == 'Add' ? true : false);
    let actionIsEdit = (action == 'Edit' ? true : false);

    $('#user-form').attr({
        'hidden' : false,
        'data-action' : action
    })

    $('#user-id')
        .attr({
            'value': user.id,
            'disabled': true,
        });
    $('#user-username')
        .attr({
            'value': user.username,
            'disabled': actionIsDelete,
            'required' : true
        });
    $('#user-password')
        .attr({
            'placeholder' : (actionIsDelete ? '' : 'Type new password if necessary'),
            'disabled': actionIsDelete,
            'required' : actionIsAdd
        })
        .removeAttr('value')
    $('#user-confirmPassword')
        .attr({
            'placeholder' : (actionIsDelete ? '' : 'Re-type new password if necessary'),
            'disabled' : actionIsDelete,
            'required' : actionIsAdd
        })
        .removeAttr('value')
    $('#user-authorities')
        .attr({
            'disabled': actionIsDelete,
            'required' : true
        })
    $('#user-authorities').html('');
    let allAuthorities = getAllAuthorities();
    $(allAuthorities).each(
        (i, authority) => {
            let userHasAuthority = false;
            $(user.authorities).each(
                (j, userAuthority) => {
                    if (userAuthority.name == authority.name && userAuthority.id == authority.id) {
                        userHasAuthority = true;
                    }
                }
            )
            $('#user-authorities').append(
                $('<option>')
                    .text(authority.name.substring(5))
                    .attr({
                        'data-id': authority.id,
                        'data-name': authority.name,
                        'data-authority': authority.authority,
                        'selected' : userHasAuthority
                    })
            )
        }
    )
    $('#user-accountNonExpired')
        .attr({
            'checked': user.accountNonExpired,
            'disabled': actionIsDelete,
        });
    $('#user-accountNonLocked')
        .attr({
            'checked': user.accountNonLocked,
            'disabled': actionIsDelete,
        });
    $('#user-credentialsNonExpired')
        .attr({
            'checked': user.credentialsNonExpired,
            'disabled': actionIsDelete,
        });
    $('#user-enabled')
        .attr({
            'checked': user.enabled,
            'disabled': actionIsDelete,
        });
    $('#user-form :button')
        .attr({
            'hidden' : actionIsAdd
        })
    $('#user-form :submit')
        .text(action)
        .attr({
            'class': (actionIsDelete ? 'btn btn-danger' : 'btn btn-info'),
            "hidden" : false
        })
}