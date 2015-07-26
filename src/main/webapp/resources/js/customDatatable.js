function makeEditable() {
    $('#add').click(function () {
        $('#item_id').val(0);
        $('#editRow').modal();
    });

    $('.delete').click(function () {
        deleteRow($(this).attr("id"));
    });

    $('#detailsForm').submit(function () {
        save();
        return false;
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
        }
    });
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        oTable_datatable.fnClearTable();
        $.each(data, function (key, item) {
            oTable_datatable.fnAddData(item);
        });
        oTable_datatable.fnDraw();
    });
}

function save() {
    var frm = $('#detailsForm');
    debugger;
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: frm.serialize(),
        success: function (data) {
            $('#editRow').modal('hide');
            updateTable();
        }
    });
}