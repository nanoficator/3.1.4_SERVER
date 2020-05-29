$(function () {
    printAllUsers();
});

$('#usersTableTabBtn').on('click', function () {
    printAllUsers();
});

function printAllUsers() {
    $.ajax({
        url: '/admin/users-table',
        type: 'get',
        success: function (data) {
            buildTable(JSON.parse(JSON.stringify(data)), getAllAuthorities());
        },
        error: function () {
            alert('ERROR');
        }
    });
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
    return allAuthorities
}

function buildTable(users, allAuthorities) {
    let content = '';
    for (let i = 0; i < users.length; i++) {
        content += '<tr id="' + users[i].id + '">';
        content += '<td>' + users[i].id + '</td>';
        content += '<td>' + users[i].username + '</td>';
        content += '<td>';
        for (let j = 0; j < users[i].authorities.length; j++) {
            content += '<div>' + users[i].authorities[j].name.substring(5) + '</div>';
        }
        content += '</td>';
        content += '<td><input type="checkbox" disabled ' + (users[i].accountNonExpired ? 'checked' : '') + '></td>';
        content += '<td><input type="checkbox" disabled ' + (users[i].accountNonLocked ? 'checked' : '') + '></td>';
        content += '<td><input type="checkbox" disabled ' + (users[i].credentialsNonExpired ? 'checked' : '') + '></td>';
        content += '<td><input type="checkbox" disabled ' + (users[i].enabled ? 'checked' : '') + '></td>';
        content += '<td>';
        content += '<button type="button" class="btn btn-info"  data-toggle="modal" data-target="#edit-user-' + users[i].id + '-modal">Edit</button>';
        content += buildEditModal(users[i], allAuthorities);
        content += '</td>';
        content += '<td>';
        content += '<button type="button" class="btn btn-danger"  data-toggle="modal" data-target="#delete-user-' + users[i].id + '-modal">Delete</button>';
        content += buildDeleteModal(users[i]);
        content += '</td>';
        content += '</tr>';
    }
    $('#usersTable tbody').html(content);
}

function buildDeleteModal(user) {
    let content = '';
    content += '<div class="modal fade" id="delete-user-' + user.id + '-modal">';
    content += '    <div class="modal-dialog">';
    content += '        <div class="modal-content">';
    content += '            <div class="modal-header">';
    content += '                <h5 class="modal-title">Delete user</h5>';
    content += '            </div>';
    content += '            <div class="modal-body">';
    content += '                <form id="delete-user-' + user.id + '" action="/admin/delete-user?id=' + user.id + '">';
    content += '                    <div class="form-group">';
    content += '                        <label for="id-delete" style="font-weight: bold">ID</label>';
    content += '                        <input type="text" class="form-control" id="id-delete" name="id" value="' + user.id + '" disabled>';
    content += '                    </div>';
    content += '                    <div class="form-group">';
    content += '                        <label for="username-delete" style="font-weight: bold">Username</label>';
    content += '                        <input type="text" class="form-control" id="username-delete" name="username" value="' + user.username + '" disabled>';
    content += '                    </div>';
    content += '                    <div class="form-group">';
    content += '                        <label for="authorities-delete" style="font-weight: bold">Roles:</label>';
    content += '                        <select class="custom-select" id="authorities-delete" multiple disabled>Roles:';
    for (i = 0; i < user.authorities.length; i++)
    {
        content += '                            <option>' + user.authorities[i].name.substring(5) + '</option>';
    }
    content += '                        </select>';
    content += '                    </div>';
    content += '                    <div class="custom-control custom-switch" align="left">';
    content += '                        <input type="checkbox" class="custom-control-input" id="accountNonExpired-delete" ' + (user.accountNonExpired ? 'checked' : '') + ' disabled>';
    content += '                        <label class="custom-control-label" for="accountNonExpired-delete" style="font-weight: bold">Is account non expired</label>';
    content += '                    </div>';
    content += '                    <div class="custom-control custom-switch" align="left">';
    content += '                        <input type="checkbox" class="custom-control-input" id="accountNonLocked-delete" ' + (user.accountNonLocked ? 'checked' : '') + ' disabled>';
    content += '                        <label class="custom-control-label" for="accountNonLocked-delete" style="font-weight: bold">Is account non locked</label>';
    content += '                    </div>';
    content += '                    <div class="custom-control custom-switch" align="left">';
    content += '                        <input type="checkbox" class="custom-control-input" id="credentialsNonExpired-delete" ' + (user.credentialsNonExpired ? 'checked' : '') + ' disabled>';
    content += '                        <label class="custom-control-label" for="credentialsNonExpired-delete" style="font-weight: bold">Is credentials non expired</label>';
    content += '                    </div>';
    content += '                    <div class="custom-control custom-switch" align="left">';
    content += '                        <input type="checkbox" class="custom-control-input" id="enabled-delete" ' + (user.enabled ? 'checked' : '') + ' disabled>';
    content += '                        <label class="custom-control-label" for="enabled-delete" style="font-weight: bold">Is enabled</label>';
    content += '                    </div>';
    content += '                    <div class="modal-footer">';
    content += '                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>';
    content += '                        <button type="submit" class="btn btn-danger">Delete</button>';
    content += '                    </div>';
    content += '                </form>';
    content += '            </div>';
    content += '        </div>';
    content += '    </div>';
    content += '</div>';
    return content;
}

function buildEditModal(user, allAuthorities) {
    let content = '';
    content += '<div class="modal fade" id="edit-user-' + user.id + '-modal">';
    content += '    <div class="modal-dialog">';
    content += '        <div class="modal-content">';
    content += '            <div class="modal-header">';
    content += '                <h5 class="modal-title">Delete user</h5>';
    content += '            </div>';
    content += '            <div class="modal-body">';
    content += '                <form id="edit-user-' + user.id + '" action="/admin/edit-user?id=' + user.id + '">';
    content += '                    <div class="form-group">';
    content += '                        <label for="id-edit" style="font-weight: bold">ID</label>';
    content += '                        <input type="text" class="form-control" id="id-edit" name="id" value="' + user.id + '" disabled>';
    content += '                    </div>';
    content += '                    <div class="form-group">';
    content += '                        <label for="username-edit" style="font-weight: bold">Username</label>';
    content += '                        <input type="text" class="form-control" id="username-edit" name="username" value="' + user.username + '" placeholder="Enter username" required>';
    content += '                    </div>';
    content += '                    <div class="form-group">';
    content += '                        <label for="password-edit" style="font-weight: bold">Username</label>';
    content += '                        <input type="password" class="form-control" id="password-edit" name="password" placeholder="Enter new password if necessary">';
    content += '                    </div>';
    content += '                    <div class="form-group">';
    content += '                        <label for="confirmPassword-edit" style="font-weight: bold">Username</label>';
    content += '                        <input type="password" class="form-control" id="confirmPassword-edit" name="confirmPassword" placeholder="Re-enter new password if necessary">';
    content += '                    </div>';
    content += '                    <div class="form-group">';
    content += '                        <label for="authorities-edit" style="font-weight: bold">Roles:</label>';
    content += '                        <select class="custom-select" id="authorities-edit" multiple>Roles:';
    for (i = 0; i < allAuthorities.length; i++) {
        content += '                            <option ' + (user.authorities.includes(allAuthorities[i].name) ? 'selected' : '') + '>' + allAuthorities[i].name.substring(5) + '</option>';
    }
    content += '                        </select>';
    content += '                    </div>';
    content += '                    <div class="custom-control custom-switch" align="left">';
    content += '                        <input type="checkbox" class="custom-control-input" id="accountNonExpired-edit" ' + (user.accountNonExpired ? 'checked' : '') + '>';
    content += '                        <label class="custom-control-label" for="accountNonExpired-edit" style="font-weight: bold">Is account non expired</label>';
    content += '                    </div>';
    content += '                    <div class="custom-control custom-switch" align="left">';
    content += '                        <input type="checkbox" class="custom-control-input" id="accountNonLocked-edit" ' + (user.accountNonLocked ? 'checked' : '') + '>';
    content += '                        <label class="custom-control-label" for="accountNonLocked-edit" style="font-weight: bold">Is account non locked</label>';
    content += '                    </div>';
    content += '                    <div class="custom-control custom-switch" align="left">';
    content += '                        <input type="checkbox" class="custom-control-input" id="credentialsNonExpired-edit" ' + (user.credentialsNonExpired ? 'checked' : '') + '>';
    content += '                        <label class="custom-control-label" for="credentialsNonExpired-edit" style="font-weight: bold">Is credentials non expired</label>';
    content += '                    </div>';
    content += '                    <div class="custom-control custom-switch" align="left">';
    content += '                        <input type="checkbox" class="custom-control-input" id="enabled-edit" ' + (user.enabled ? 'checked' : '') + '>';
    content += '                        <label class="custom-control-label" for="enabled-edit" style="font-weight: bold">Is enabled</label>';
    content += '                    </div>';
    content += '                    <div class="modal-footer">';
    content += '                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>';
    content += '                        <button type="submit" class="btn btn-info">Edit</button>';
    content += '                    </div>';
    content += '                </form>';
    content += '            </div>';
    content += '        </div>';
    content += '    </div>';
    content += '</div>';
    return content;
}