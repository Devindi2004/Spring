$(document).ready(function () {
    getAllCustomers();

    $(document).on("click", "#customerTable tr", function () {
        $("#customerId").val($(this).find("td:eq(0)").text());
        $("#customerName").val($(this).find("td:eq(1)").text());
        $("#customerAddress").val($(this).find("td:eq(2)").text());
        $("#customerAge").val($(this).find("td:eq(3)").text());
    });
});

function getAllCustomers() {
    $("#customerTable").empty();
    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "GET",
        success: function (res) {
            for (let c of res.data) {
                $("#customerTable").append(`<tr><td>${c.cId}</td><td>${c.cName}</td><td>${c.cAddress}</td><td>${c.cAge}</td></tr>`);
            }
        }
    });
}

function saveCustomer() {
    // Data object eka DTO ekata 100% match wenna ona
    let customerDTO = {
        cId: 0, // Auto-increment nisa 0 yawanna
        cName: $("#customerName").val(),
        cAddress: $("#customerAddress").val(),
        cAge: parseInt($("#customerAge").val())
    };

    // Validation (SweetAlert2)
    if (!customerDTO.cName || !customerDTO.cAddress) {
        Swal.fire('Required!', 'Please fill Name and Address', 'warning');
        return;
    }

    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(customerDTO),
        success: function (res) {
            Swal.fire('Saved!', 'Customer has been added.', 'success');
            resetForm();
            getAllCustomers();
        },
        error: function (ob) {
            // 400 Bad Request error eke details meken ganna puluwan
            console.log(ob);
            let message = ob.responseJSON ? ob.responseJSON.message : "Internal Error";
            Swal.fire('Error 400', 'Invalid Data: ' + message, 'error');
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

    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(customer),
        success: function () {
            Swal.fire('Updated!', 'Customer updated successfully.', 'success');
            resetForm();
            getAllCustomers();
        }
    });
}

function deleteCustomer() {
    let cId = $("#customerId").val();
    Swal.fire({
        title: 'Are you sure?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "http://localhost:8080/api/v1/customer/" + cId,
                method: "DELETE",
                success: function () {
                    Swal.fire('Deleted!', 'Customer has been deleted.', 'success');
                    resetForm();
                    getAllCustomers();
                }
            });
        }
    });
}

function resetForm() {
    $("#customerId, #customerName, #customerAddress, #customerAge").val("");
}