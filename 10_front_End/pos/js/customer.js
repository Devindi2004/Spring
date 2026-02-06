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
function updateCustomer(){
    let cId = $('customerId').val();
    let cName = $('customerName').val();
    let cAddress = $('customerAddress').val();
    let cAge = $('customerAge').val();

    console.log(cId,cName,cAddress,cAge)
    $.ajax({
        url:'http://localhost:8080/api/v1/customer',
        method:'POST',
        contentType:"application/json",
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