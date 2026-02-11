function saveCustomer(){
    let cId = $('#customerId').val();
    let cName = $('#customerName').val();
    let cAddress = $('#customerAddress').val();
    let cAge = $('#customerAge').val();

    console.log("cid=",cId,cName,cAddress,cAge)
    $.ajax({
        url:'http://localhost:8080/api/v1/customer',
        method:'POST',
        contentType: "application/json",
        data: JSON.stringify({
            "cId": cId,
            "cName": cName,
            "cAddress": cAddress,
            "cAge": cAge
        }),
        success:function (res){
            alert("done")
        },
        error:function (error){
            alert("error")
        }
    })
}
function updateCustomer() {
    let cId = $('#customerId').val();
    let cName = $('#customerName').val();
    let cAddress = $('#customerAddress').val();
    let cAge = $('#customerAge').val();

    console.log(cId, cName, cAddress, cAge)
    $.ajax({
        url: 'http://localhost:8080/api/v1/customer',
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify({
            "cId": cId,
            "cName": cName,
            "cAddress": cAddress,
            "cAge": cAge
        }),
        success: function (res) {
            alert("done")
        },
        error: function (error) {
            alert("error")
        }
    })
}
function deleteCustomer() {
        let cId = $('#customerId').val();
        console.log(cId);

        $.ajax({
            url:'http://localhost:8080/api/v1/customer',
            method: 'DELETE',
            contentType: "application/json",
            data: JSON.stringify({
                "cId": cId
            }),
            success:function (res){
                alert("done")
            },
            error:function (error){
                alert("error")
            }
        })
    }
$(document).ready(function () {
    getAllCustomers();
});
function getAllCustomers() {
    $(".customerTable tbody").empty();

    $.ajax({
        url: "http://localhost:8080/api/v1/customer",
        method: "GET",
        success: function (res) {
            console.log(res)
            for (let c of res) {
                console.log(c , " testing")
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
            alert("Failed to load customers");
        }
    });
}
