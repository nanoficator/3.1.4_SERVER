$(function () {
    printAllUsers();
})

$('#usersTableTabBtn').on('click', function () {
    printAllUsers();
});

function printAllUsers() {
    $.ajax({
        url: '/admin/users-table',
        type: 'get',
        success: function (json) {
            buildTable(JSON.parse(JSON.stringify(json)));
        },
        error: function () {
            alert('ERROR');
        }
    });
}

function buildTable(data) {
    let tBody = document.getElementById('usersTable').tBodies[0];
    data.forEach(function (item) {
        let row = tBody.insertRow();
        for (key in item) {
            if (key != 'password' && key != 'confirmPassword') {
                let cell = row.insertCell();
                cell.innerHTML = item[key];
            }
        }
        let editCell = row.insertCell();
        editCell.innerHTML = 'Edit';
        let deleteCell = row.insertCell();
        deleteCell.innerHTML = 'Delete';

    });
}