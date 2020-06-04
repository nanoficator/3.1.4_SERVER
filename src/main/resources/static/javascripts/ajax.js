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

function getAuthorityByName(name) {
    let authority;
    $.ajax({
        url: '/admin/authority-by-name',
        type: 'get',
        async: false,
        data: name,
        success: function (data) {
            authority = JSON.parse(JSON.stringify(data));
        }
    });
    return authority;
}