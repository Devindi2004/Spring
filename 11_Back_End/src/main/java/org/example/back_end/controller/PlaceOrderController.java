package org.example.back_end.controller;

import lombok.RequiredArgsConstructor;
import org.example.back_end.dto.PlaceOrderDTO;
import org.example.back_end.entity.PlaceOrder;
import org.example.back_end.service.custom.PlaceOrderService;
import org.example.back_end.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
@CrossOrigin
public class PlaceOrderController {

    private final PlaceOrderService placeOrderService;

    @PostMapping
    public ResponseEntity<APIResponse<String>> saveOrder(@RequestBody PlaceOrderDTO dto){
        placeOrderService.saveOrder(dto);
        return new ResponseEntity<>(new APIResponse<>(201, "Order Placed Successfully", null), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<APIResponse<String>> updateOrder(@RequestBody PlaceOrderDTO dto){
        placeOrderService.updateOrder(dto);
        return new ResponseEntity<>(new APIResponse<>(200, "Order Updated Successfully", null), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<PlaceOrder>>> getAllOrders(){
        return new ResponseEntity<>(new APIResponse<>(200, "Success", placeOrderService.getOrderData()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteOrder(@PathVariable String id){
        placeOrderService.deleteOrderById(id);
        return new ResponseEntity<>(new APIResponse<>(200, "Order Deleted Successfully", null), HttpStatus.OK);
    }
}