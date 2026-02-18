$(document).ready(function () {
    loadCustomers();
    loadItems();
    getAllOrders();
});

function loadCustomers() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/customer',
        method: 'GET',
        success: function (res) {
            $("#customerId").empty().append(`<option value="">Select Customer</option>`);
            for (let c of res.data) {
                $("#customerId").append(`<option value="${c.cId}">${c.cId} - ${c.cName}</option>`);
            }
        }
    });
}

function loadItems() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/item',
        method: 'GET',
        success: function (res) {
            $("#itemId").empty().append(`<option value="">Select Item</option>`);
            for (let i of res.data) {
                $("#itemId").append(`<option value="${i.itemId}">${i.itemId} - ${i.itemName}</option>`);
            }
        }
    });
}

function saveOrder() {
    let order = {
        orderId: $('#orderId').val(),
        customerId: $('#customerId').val(),
        itemId: $('#itemId').val(),
        orderQty: parseInt($('#orderQty').val()),
        orderPrice: parseFloat($('#orderPrice').val())
    };

    $.ajax({
        url: 'http://localhost:8080/api/v1/order',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(order),
        success: function () {
            Swal.fire('Success!', 'Order placed successfully.', 'success');
            getAllOrders();
            resetForm();
        },
        error: function (err) {
            // Backend exception message eka pennanawa
            Swal.fire('Failed!', 'Check stock or order details.', 'error');
        }
    });
}

function getAllOrders() {
    $("#orderTable").empty();
    $.ajax({
        url: 'http://localhost:8080/api/v1/order',
        method: 'GET',
        success: function (res) {
            for (let o of res.data) {
                let row = `<tr>
                    <td>${o.orderId}</td>
                    <td>${o.customer ? o.customer.cId : 'N/A'}</td>
                    <td>${o.item ? o.item.itemId : 'N/A'}</td>
                    <td>${o.orderQty}</td>
                    <td>${o.orderPrice}</td>
                </tr>`;
                $("#orderTable").append(row);
            }
        }
    });
}

function deleteOrder() {
    let orderId = $('#orderId').val();
    $.ajax({
        url: 'http://localhost:8080/api/v1/order/' + orderId,
        method: 'DELETE',
        success: function () {
            Swal.fire('Cancelled!', 'Order has been removed and stock reverted.', 'success');
            getAllOrders();
            resetForm();
        }
    });
}

function resetForm() {
    $('#orderId, #customerId, #itemId, #orderQty, #orderPrice').val("");
}