function getAllUsers() {
    let allUsers;
    $.ajax({
        url: '/admin/all-users',
        type: 'get',
        async: false,
        success: (data) => {
            allUsers = JSON.parse(JSON.stringify(data));
        }
    });
    return allUsers;
}

function getAllAuthorities() {
    let allAuthorities;
    $.ajax({
        url: '/admin/all-authorities',
        type: 'get',
        async: false,
        success: (data) => {
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
        success: (data) => {
            authority = JSON.parse(JSON.stringify(data));
        }
    });
    return authority;
}