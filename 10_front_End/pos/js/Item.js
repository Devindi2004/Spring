$(document).ready(function () {
    getAllItems();

    $(document).on("click", "#itemTable tr", function () {
        $("#itemId").val($(this).find("td:eq(0)").text());
        $("#itemName").val($(this).find("td:eq(1)").text());
        $("#itemPrice").val($(this).find("td:eq(2)").text());
        $("#itemQty").val($(this).find("td:eq(3)").text());
    });
});

function getAllItems() {
    $("#itemTable").empty();
    $.ajax({
        url: "http://localhost:8080/api/v1/item",
        method: "GET",
        success: function (res) {
            for (let i of res.data) {
                $("#itemTable").append(`<tr><td>${i.itemId}</td><td>${i.itemName}</td><td>${i.itemPrice}</td><td>${i.itemQty}</td></tr>`);
            }
        }
    });
}

function saveItem() {
    let item = {
        itemId: $("#itemId").val(),
        itemName: $("#itemName").val(),
        itemPrice: parseFloat($("#itemPrice").val()),
        itemQty: parseInt($("#itemQty").val())
    };

    $.ajax({
        url: "http://localhost:8080/api/v1/item",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(item),
        success: function () {
            Swal.fire('Success!', 'Item added successfully.', 'success');
            resetForm();
            getAllItems();
        }
    });
}

function updateItem() {
    let item = {
        itemId: $("#itemId").val(),
        itemName: $("#itemName").val(),
        itemPrice: $("#itemPrice").val(),
        itemQty: $("#itemQty").val()
    };

    $.ajax({
        url: "http://localhost:8080/api/v1/item",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(item),
        success: function () {
            Swal.fire('Updated!', 'Item details updated.', 'success');
            resetForm();
            getAllItems();
        }
    });
}

function deleteItem() {
    let itemId = $("#itemId").val();
    $.ajax({
        url: "http://localhost:8080/api/v1/item/" + itemId,
        method: "DELETE",
        success: function () {
            Swal.fire('Deleted!', 'Item removed successfully.', 'success');
            resetForm();
            getAllItems();
        }
    });
}

function resetForm() {
    $("#itemId, #itemName, #itemPrice, #itemQty").val("");
}