$('#modal-button').on('click', function (event) {
    let action = event.target.attributes.name.nodeValue;
    if (action == 'delete') {
        let formdata = $("#user-form").serializeArray();
        let data = {};
        $(formdata).each(function(index, obj){
            data[obj.name] = obj.value;
        });
        deleteUser(user);
    } else if (action == 'edit') {
        editUser(user);
    }
})

function deleteUser(user) {
    $.ajax({
        url: '/admin/delete-user',
        data: user,
        type: 'post',
        success: function (data) {
            alert(data);
        },
        error: function () {
            alert('error');
        }
    })
}