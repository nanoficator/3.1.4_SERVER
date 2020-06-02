$(function () {
    updateUsersTable(
        getAllUsers(),
        getAllAuthorities()
    );
});

$('#usersTableTabBtn').on('click', function () {
    updateUsersTable(
        getAllUsers(),
        getAllAuthorities()
    );
});

function getAllUsers() {
    let allUsers;
    $.ajax({
        url: '/admin/users-table',
        type: 'get',
        async: false,
        success: function (data) {
            allUsers = JSON.parse(JSON.stringify(data));
        }
    });
    return allUsers;
}

function getAllAuthorities() {
    let allAuthorities;
    $.ajax({
        url: '/admin/authorities',
        type: 'get',
        async: false,
        success: function (data) {
            allAuthorities = JSON.parse(JSON.stringify(data));
        }
    });
    return allAuthorities;
}

function updateUsersTable(allUsers, allAuthorities) {
    $('#users-table tbody').html('');
    $(allUsers).each(function (i, user) {
        $('#users-table tbody').append($('<tr>').attr({'id' : 'row-' + user.id})).append(
            $('<td>').text(user.id),
            $('<td>').text(user.username),
            $('<td>').text(
                function () {
                    let res = '';
                    $(user.authorities).each(function (j, authority) {
                        res += authority.name.substring(5) + ' ';
                    });
                    return res;
                }
            ),
            $('<td>')
                .append($('<input type="checkbox" disabled>')
                    .attr({
                        'checked': user.accountNonExpired
                    })),
            $('<td>')
                .append($('<input type="checkbox" disabled>')
                    .attr({
                        'checked': user.accountNonLocked
                    })),
            $('<td>')
                .append($('<input type="checkbox" disabled>')
                    .attr({
                        'checked': user.credentialsNonExpired
                    })),
            $('<td>')
                .append($('<input type="checkbox" disabled>')
                    .attr({
                        'checked': user.enabled
                    })),
            $('<td>')
                .append($('<button>')
                    .text('Edit')
                    .attr({
                        'type': 'button',
                        'class': 'btn btn-info',
                        'data-toggle': 'modal',
                        'data-target': '#user-modal'
                    })
                    .data('user', user)
                ),
            $('<td>')
                .append($('<button>')
                    .text('Delete')
                    .attr({
                        'type': 'button',
                        'class': 'btn btn-danger',
                        'data-toggle': 'modal',
                        'data-target': '#user-modal'
                    })
                    .data('user', user)
                )
        )
    })
}