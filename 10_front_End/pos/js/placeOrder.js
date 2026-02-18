let cart = [];

$(document).ready(function () {
    loadCustomers();
    loadItems();
    getAllOrders();
});


// ==========================
// 1. LOAD CUSTOMERS
// ==========================
function loadCustomers() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/customer',
        method: 'GET',
        success: function (res) {
            $("#customerId").empty().append(`<option value="">Select Customer</option>`);

            if (res.data) {
                res.data.forEach(c => {
                    $("#customerId").append(
                        `<option value="${c.cId}">
                            ${c.cId} - ${c.cName}
                        </option>`
                    );
                });
            }
        }
    });
}


// ==========================
// 2. LOAD ITEMS
// ==========================
function loadItems() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/item',
        method: 'GET',
        success: function (res) {

            $("#itemId").empty().append(`<option value="">Select Item</option>`);

            if (res.data) {
                res.data.forEach(i => {
                    $("#itemId").append(
                        `<option value="${i.itemId}">
                            ${i.itemId} - ${i.itemName}
                        </option>`
                    );
                });
            }
        }
    });
}


// ==========================
// 3. ITEM CHANGE EVENT
// ==========================
$(document).on("change", "#itemId", function () {

    let itemId = $(this).val();

    if (!itemId) {
        $("#unitPrice, #qtyOnHand").val("");
        $("#stockStatus").hide();
        return;
    }

    $.ajax({
        url: 'http://localhost:8080/api/v1/item',
        method: 'GET',
        success: function (res) {

            let item = res.data.find(i => i.itemId == itemId);

            if (item) {

                $('#unitPrice').val(item.itemPrice);
                $('#qtyOnHand').val(item.itemQty);

                let status = $('#stockStatus');

                if (item.itemQty > 0) {
                    status.text("AVAILABLE")
                        .css({
                            "background-color": "#d4edda",
                            "color": "#155724",
                            "display": "inline-block",
                            "padding": "4px 10px",
                            "border-radius": "6px"
                        }).show();
                } else {
                    status.text("OUT OF STOCK")
                        .css({
                            "background-color": "#f8d7da",
                            "color": "#721c24",
                            "display": "inline-block",
                            "padding": "4px 10px",
                            "border-radius": "6px"
                        }).show();
                }
            }
        }
    });
});


// ==========================
// 4. ADD TO CART
// ==========================
function addToCart() {

    let itemId = $('#itemId').val();
    let itemName = $('#itemId option:selected').text().split(' - ')[1];
    let qtyOnHand = parseInt($('#qtyOnHand').val());
    let orderQty = parseInt($('#orderQty').val());
    let unitPrice = parseFloat($('#unitPrice').val());

    if (!itemId || isNaN(orderQty) || orderQty <= 0) {
        Swal.fire('Error', 'Please select item and valid quantity!', 'error');
        return;
    }

    if (orderQty > qtyOnHand) {
        Swal.fire('Out of Stock', 'Insufficient stock available!', 'warning');
        return;
    }

    let existingItem = cart.find(i => i.itemId == itemId);

    if (existingItem) {

        let newQty = existingItem.qty + orderQty;

        if (newQty > qtyOnHand) {
            Swal.fire('Error', 'Total cart quantity exceeds stock!', 'error');
            return;
        }

        existingItem.qty = newQty;

    } else {

        cart.push({
            itemId: parseInt(itemId),
            itemName: itemName,
            qty: orderQty,
            unitPrice: unitPrice
        });
    }

    renderCart();
    $("#orderQty").val("");
}


// ==========================
// 5. RENDER CART
// ==========================
function renderCart() {

    $('#cartTable').empty();
    let netTotal = 0;

    cart.forEach((item, index) => {

        let total = item.qty * item.unitPrice;
        netTotal += total;

        $('#cartTable').append(`
            <tr>
                <td>${item.itemId}</td>
                <td>${item.itemName}</td>
                <td>${item.unitPrice.toFixed(2)}</td>
                <td>${item.qty}</td>
                <td>${total.toFixed(2)}</td>
                <td>
                    <button class="btn btn-sm btn-danger"
                        onclick="removeFromCart(${index})">
                        Remove
                    </button>
                </td>
            </tr>
        `);
    });

    $('#netTotal').text(netTotal.toFixed(2));
    $('#itemCount').text(cart.length);
}


// ==========================
// 6. REMOVE FROM CART
// ==========================
function removeFromCart(index) {
    cart.splice(index, 1);
    renderCart();
}

function clearCart() {
    cart = [];
    renderCart();
}


// ==========================
// 7. PLACE ORDER
// ==========================
function placeOrder() {

    let customerId = $('#customerId').val();

    if (!customerId || cart.length === 0) {
        Swal.fire('Wait!', 'Select a customer and add items to cart!', 'warning');
        return;
    }

    let orderData = {
        orderId: 0,
        customerId: parseInt(customerId),
        items: cart.map(i => ({
            itemId: i.itemId,
            qty: i.qty,
            unitPrice: i.unitPrice
        }))
    };

    $.ajax({
        url: 'http://localhost:8080/api/v1/order',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify(orderData),

        success: function () {
            Swal.fire('Success', 'Order Placed Successfully!', 'success');
            clearCart();
            getAllOrders();
            loadItems();
        },

        error: function (xhr) {
            Swal.fire('Failed!', 'Error: ' + xhr.responseText, 'error');
        }
    });
}


// ==========================
// 8. GET ALL ORDERS (HISTORY)
// ==========================
function getAllOrders() {

    $("#orderTable").empty();

    $.ajax({
        url: 'http://localhost:8080/api/v1/order',
        method: 'GET',
        dataType: 'json',

        success: function (res) {

            if (res.data && res.data.length > 0) {

                res.data.forEach(o => {

                    $("#orderTable").append(`
                        <tr>
                            <td>${o.orderId}</td>
                            <td>${o.customerName}</td>
                            <td>${o.date}</td>
                            <td class="fw-bold text-danger">
                                ${o.totalPrice.toFixed(2)}
                            </td>
                            <td>
                                <button class="btn btn-sm btn-outline-danger"
                                    onclick="deleteOrder(${o.orderId})">
                                    Cancel
                                </button>
                            </td>
                        </tr>
                    `);
                });
            }
        },

        error: function (xhr, status, error) {
            console.error("History loading error: ", status, error);
        }
    });
}


// ==========================
// 9. DELETE ORDER
// ==========================
function deleteOrder(orderId) {

    Swal.fire({
        title: 'Cancel Order?',
        text: "This will revert stock and delete record!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        confirmButtonText: 'Yes, Cancel it!'
    }).then((result) => {

        if (result.isConfirmed) {

            $.ajax({
                url: `http://localhost:8080/api/v1/order/${orderId}`,
                method: 'DELETE',

                success: function () {
                    Swal.fire('Deleted!', 'Order cancelled successfully.', 'success');
                    getAllOrders();
                    loadItems();
                }
            });
        }
    });
}
