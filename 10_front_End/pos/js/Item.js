$(document).ready(function () {

    getAllItems();

    $(document).on("click", "#itemTable tr", function () {

        let itemId = $(this).find("td:eq(0)").text();
        let itemName = $(this).find("td:eq(1)").text();
        let itemPrice = $(this).find("td:eq(2)").text();
        let itemQty = $(this).find("td:eq(3)").text();

        $("#itemId").val(itemId);
        $("#itemName").val(itemName);
        $("#itemPrice").val(itemPrice);
        $("#itemQty").val(itemQty);
    });

});

function saveItem() {

    let item = {
        itemId: $("#itemId").val(),
        itemName: $("#itemName").val(),
        itemPrice: parseFloat($("#itemPrice").val()),
        itemQty: parseInt($("#itemQty").val())
    };

    if (!item.itemId || !item.itemName) {
        alert("Item ID & Name required!");
        return;
    }

    $.ajax({
        url: "http://localhost:8080/api/v1/item",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(item),
        success: function () {
            alert("Item Saved Successfully");
            resetForm();
            getAllItems();
        },
        error: function (err) {
            console.log(err);
            alert("Error Saving Item");
        }
    });
}


function updateItem() {

    let item = {
        itemId: $("#itemId").val(),
        itemName: $("#itemName").val(),
        itemPrice: parseFloat($("#itemPrice").val()),
        itemQty: parseInt($("#itemQty").val())
    };

    if (!item.itemId) {
        alert("Select Item to Update");
        return;
    }

    $.ajax({
        url: "http://localhost:8080/api/v1/item",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(item),
        success: function () {
            alert("Item Updated Successfully");
            resetForm();
            getAllItems();
        },
        error: function (err) {
            console.log(err);
            alert("Error Updating Item");
        }
    });
}


function deleteItem() {

    let itemId = $("#itemId").val();

    if (!itemId) {
        alert("Select Item to Delete");
        return;
    }

    $.ajax({
        url: "http://localhost:8080/api/v1/item/" + itemId, // ✅ FIXED
        method: "DELETE",
        success: function () {
            alert("Item Deleted Successfully");
            resetForm();
            getAllItems();
        },
        error: function (err) {
            console.log(err);
            alert("Error Deleting Item");
        }
    });
}


function getAllItems() {

    $("#itemTable").empty();

    $.ajax({
        url: "http://localhost:8080/api/v1/item",
        method: "GET",
        success: function (res) {

            for (let item of res) {

                let row = `
                    <tr>
                        <td>${item.itemId}</td>
                        <td>${item.itemName}</td>
                        <td>${item.itemPrice}</td>
                        <td>${item.itemQty}</td>
                    </tr>
                `;

                $("#itemTable").append(row);
            }
        },
        error: function (err) {
            console.log(err);
            alert("Failed to Load Items");
        }
    });
}


function resetForm() {
    $("#itemId").val("");
    $("#itemName").val("");
    $("#itemPrice").val("");
    $("#itemQty").val("");
}
