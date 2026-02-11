$(document).ready(function () {
    loadCustomers();
    loadItems();
    getAllOrders();
});

/* ================= LOAD CUSTOMER ================= */

function loadCustomers() {

    $("#customerId").empty();
    $("#customerId").append(`<option value="">Select Customer</option>`);

    $.ajax({
        url: 'http://localhost:8080/api/v1/customer',
        method: 'GET',
        success: function (res) {

            for (let c of res) {
                $("#customerId").append(
                    `<option value="${c.cId}">${c.cId}</option>`
                );
            }
        },
        error: function (err) {
            console.log(err);
            alert("Failed to load customers");
        }
    });
}

/* ================= LOAD ITEM ================= */

function loadItems() {

    $("#itemId").empty();
    $("#itemId").append(`<option value="">Select Item</option>`);

    $.ajax({
        url: 'http://localhost:8080/api/v1/item',
        method: 'GET',
        success: function (res) {

            for (let i of res) {
                $("#itemId").append(
                    `<option value="${i.itemId}">${i.itemId}</option>`
                );
            }
        },
        error: function (err) {
            console.log(err);
            alert("Failed to load items");
        }
    });
}

/* ================= SAVE ORDER ================= */

function saveOrder() {

    let orderId = $('#orderId').val();
    let customerId = $('#customerId').val();
    let itemId = $('#itemId').val();
    let orderPrice = $('#orderPrice').val();
    let orderQty = $('#orderQty').val();

    if (orderId === "" || customerId === "" || itemId === "") {
        alert("Please select Customer & Item!");
        return;
    }

    $.ajax({
        url: 'http://localhost:8080/api/v1/order',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify({
            orderId: orderId,
            customerId: customerId,
            itemId: itemId,
            orderQty: parseInt(orderQty),
            orderPrice: parseFloat(orderPrice)
        }),
        success: function () {
            alert("Order Saved Successfully");
            getAllOrders();
            resetForm();
        },
        error: function (err) {
            console.log(err);
            alert("Error Saving Order");
        }
    });
}

/* ================= UPDATE ORDER ================= */

function updateOrder() {

    let orderId = $('#orderId').val();
    let customerId = $('#customerId').val();
    let itemId = $('#itemId').val();
    let orderPrice = $('#orderPrice').val();
    let orderQty = $('#orderQty').val();

    $.ajax({
        url: 'http://localhost:8080/api/v1/order',
        method: 'PUT',
        contentType: "application/json",
        data: JSON.stringify({
            orderId: orderId,
            customerId: customerId,
            itemId: itemId,
            orderQty: parseInt(orderQty),
            orderPrice: parseFloat(orderPrice)
        }),
        success: function () {
            alert("Order Updated Successfully");
            getAllOrders();
            resetForm();
        },
        error: function (err) {
            console.log(err);
            alert("Error Updating Order");
        }
    });
}

/* ================= DELETE ORDER ================= */

function deleteOrder() {

    let orderId = $('#orderId').val();

    if (orderId === "") {
        alert("Enter Order ID to delete");
        return;
    }

    $.ajax({
        url: 'http://localhost:8080/api/v1/order/' + orderId,
        method: 'DELETE',
        success: function () {
            alert("Order Deleted Successfully");
            getAllOrders();
            resetForm();
        },
        error: function (err) {
            console.log(err);
            alert("Error Deleting Order");
        }
    });
}

/* ================= GET ALL ORDERS ================= */

function getAllOrders() {

    $("#orderTable").empty();

    $.ajax({
        url: 'http://localhost:8080/api/v1/order',
        method: 'GET',
        success: function (res) {

            for (let o of res) {

                let row = `
                    <tr>
                        <td>${o.orderId}</td>
                        <td>${o.customerId}</td>
                        <td>${o.itemId}</td>
                        <td>${o.orderQty}</td>
                        <td>${o.orderPrice}</td>
                    </tr>
                `;

                $("#orderTable").append(row);
            }
        },
        error: function (err) {
            console.log(err);
            alert("Failed to Load Orders");
        }
    });
}

/* ================= TABLE CLICK ================= */

$(document).on("click", "#orderTable tr", function () {

    let cols = $(this).children("td");

    $('#orderId').val(cols.eq(0).text());
    $('#customerId').val(cols.eq(1).text());
    $('#itemId').val(cols.eq(2).text());
    $('#orderQty').val(cols.eq(3).text());
    $('#orderPrice').val(cols.eq(4).text());
});

/* ================= RESET ================= */

function resetForm() {
    $('#orderId').val("");
    $('#customerId').val("");
    $('#itemId').val("");
    $('#orderQty').val("");
    $('#orderPrice').val("");
}
