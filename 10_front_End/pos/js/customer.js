const BASE_URL = "http://localhost:8080/api/v1/customer";

$(document).ready(function () {

    loadCustomers();

    $(document).on("click", "#customerTable tr", function () {

        let columns = $(this).children("td");

        if (columns.length === 0) return;

        $("#customerId").val(columns.eq(0).text());
        $("#customerName").val(columns.eq(1).text());
        $("#customerAddress").val(columns.eq(2).text());
        $("#customerAge").val(columns.eq(3).text());
    });
});

function saveCustomer() {

    let name = $("#customerName").val().trim();
    let address = $("#customerAddress").val().trim();
    let age = $("#customerAge").val();

    if (!name) {
        alert("Customer Name is required!");
        return;
    }

    if (!age || isNaN(age) || age <= 0) {
        alert("Valid Age is required!");
        return;
    }

    let customer = {
        cName: name,
        cAddress: address,
        cAge: parseInt(age)
    };

    $.ajax({
        url: BASE_URL,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(customer),
        success: function () {
            alert("Customer Saved Successfully");
            resetForm();
            loadCustomers();
        },
        error: function (err) {
            console.error(err);
            alert("Error Saving Customer");
        }
    });
}

function updateCustomer() {

    let cId = $("#customerId").val();
    let name = $("#customerName").val().trim();
    let address = $("#customerAddress").val().trim();
    let age = $("#customerAge").val();

    if (!cId) {
        alert("Select a customer first!");
        return;
    }

    if (!name) {
        alert("Customer Name is required!");
        return;
    }

    if (!age || isNaN(age) || age <= 0) {
        alert("Valid Age is required!");
        return;
    }

    let customer = {
        cId: parseInt(cId),
        cName: name,
        cAddress: address,
        cAge: parseInt(age)
    };

    $.ajax({
        url: BASE_URL,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(customer),
        success: function () {
            alert("Customer Updated Successfully");
            resetForm();
            loadCustomers();
        },
        error: function (err) {
            console.error(err);
            alert("Error Updating Customer");
        }
    });
}

function deleteCustomer() {

    let cId = $("#customerId").val();

    if (!cId) {
        alert("Select a customer first!");
        return;
    }

    if (!confirm("Are you sure you want to delete this customer?")) {
        return;
    }

    $.ajax({
        url: BASE_URL,
        type: "DELETE",
        contentType: "application/json",
        data: JSON.stringify({ cId: parseInt(cId) }),
        success: function () {
            alert("Customer Deleted Successfully");
            resetForm();
            loadCustomers();
        },
        error: function (err) {
            console.error(err);
            alert("Error Deleting Customer");
        }
    });
}


function loadCustomers() {

    $("#customerTable").empty();

    $.ajax({
        url: BASE_URL,
        type: "GET",
        success: function (res) {

            let customers = [];

            if (res.data) {
                customers = res.data;
            } else {
                customers = res;
            }

            if (!Array.isArray(customers)) {
                console.error("Unexpected response format", res);
                return;
            }

            customers.forEach(function (c) {

                let row = `
                    <tr>
                        <td>${c.cId}</td>
                        <td>${c.cName}</td>
                        <td>${c.cAddress}</td>
                        <td>${c.cAge}</td>
                    </tr>
                `;

                $("#customerTable").append(row);
            });
        },
        error: function (err) {
            console.error(err);
            alert("Failed to Load Customers");
        }
    });
}

function resetForm() {
    $("#customerId").val("");
    $("#customerName").val("");
    $("#customerAddress").val("");
    $("#customerAge").val("");
}
