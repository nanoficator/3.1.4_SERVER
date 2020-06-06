$.ajaxSetup({
    beforeSend: function(xhr) {
        xhr.setRequestHeader($('#_csrf_header').attr('content'), $('#_csrf').attr('content'));
    },
    contentType: 'application/json'
});

function getAllUsers() {
    let allUsers;
    $.ajax({
        url: '/admin/all-users',
        type: 'get',
        async: false,
        success: (data) => {
            allUsers = data;
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
            allAuthorities = data;
        }
    });
    return allAuthorities;
}

function deleteUser(user) {
    let message;
    $.ajax({
        url: '/admin/delete-user',
        data: JSON.stringify(user),
        type: 'post',
        async: false,
        success: function (data) {
            message = data;
            alert(message);
        },
        error: function (data) {
            message = data
            alert(message);
        }
    })
}

function editUser(user) {
    let message;
    $.ajax({
        url: '/admin/edit-user',
        data: JSON.stringify(user),
        type: 'post',
        async: false,
        success: function (data) {
            message = data.responseText
            alert(message);
        },
        error: function (data) {
            message = data.responseText
            alert(message);
        }
    })
    return message;
}

function addUser(user) {
    let message;
    $.ajax({
        url: '/admin/add-user',
        data: JSON.stringify(user),
        type: 'post',
        async: false,
        success: function (data) {
            message = data;
            alert(message);
        },
        error: function (data) {
            message = data.responseText
            alert(message);
        }
    })
    return message;
}