$(document).ready(function () {

    getAllCustomers();


    $(document).on("click", "#customerTable tr", function () {

        let cId = $(this).find("td:eq(0)").text();
        let cName = $(this).find("td:eq(1)").text();
        let cAddress = $(this).find("td:eq(2)").text();
        let cAge = $(this).find("td:eq(3)").text();

        $("#customerId").val(cId);
        $("#customerName").val(cName);
        $("#customerAddress").val(cAddress);
        $("#customerAge").val(cAge);
    });

});

function saveCustomer() {

    let customer = {
        cId: $("#customerId").val(),
        cName: $("#customerName").val(),
        cAddress: $("#customerAddress").val(),
        cAge: $("#customerAge").val()
    };

    if (!customer.cId || !customer.cName) {
        alert("Customer ID & Name required!");
        return;
    }

    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(customer),
        success: function () {
            alert("Customer Saved Successfully");
            resetForm();
            getAllCustomers();
        },
        error: function (err) {
            console.log(err);
            alert("Error Saving Customer");
        }
    });
}

function updateCustomer() {

    let customer = {
        cId: $("#customerId").val(),
        cName: $("#customerName").val(),
        cAddress: $("#customerAddress").val(),
        cAge: $("#customerAge").val()
    };

    if (!customer.cId) {
        alert("Select Customer to Update");
        return;
    }

    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "PUT",   // FIXED (was POST before)
        contentType: "application/json",
        data: JSON.stringify(customer),
        success: function () {
            alert("Customer Updated Successfully");
            resetForm();
            getAllCustomers();
        },
        error: function (err) {
            console.log(err);
            alert("Error Updating Customer");
        }
    });
}

function deleteCustomer() {

    let cId = $("#customerId").val();

    if (!cId) {
        alert("Select Customer to Delete");
        return;
    }

    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "DELETE",
        contentType: "application/json",
        data: JSON.stringify({ cId: cId }),
        success: function () {
            alert("Customer Deleted Successfully");
            resetForm();
            getAllCustomers();
        },
        error: function (err) {
            console.log(err);
            alert("Error Deleting Customer");
        }
    });
}

function getAllCustomers() {

    $("#customerTable").empty();  // FIXED clearing

    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "GET",
        success: function (res) {

            for (let c of res) {

                let row = `
                    <tr>
                        <td>${c.cId}</td>
                        <td>${c.cName}</td>
                        <td>${c.cAddress}</td>
                        <td>${c.cAge}</td>
                    </tr>
                `;

                $("#customerTable").append(row);
            }
        },
        error: function (err) {
            console.log(err);
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
