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

function getAuthorityById(id) {
    let authority;
    $.ajax({
        url: '/admin/authority-by-id',
        type: 'get',
        async: false,
        data: id,
        success: function (data) {
            authority = JSON.parse(JSON.stringify(data));
        }
    });
    return authority;
}