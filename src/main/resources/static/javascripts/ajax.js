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
    $.ajax({
        url: '/admin/delete-user',
        data: JSON.stringify(user),
        type: 'post',
        success: function (data) {
            alert(data);
        },
        error: function () {
            alert('error');
        }
    })
}

function editUser(user) {
    $.ajax({
        url: '/admin/edit-user',
        data: JSON.stringify(user),
        type: 'post',
        success: function (data) {
            alert(data);
        },
        error: function () {
            alert('error');
        }
    })
}