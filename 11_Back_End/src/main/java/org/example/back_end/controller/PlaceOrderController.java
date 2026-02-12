package org.example.back_end.controller;

import lombok.RequiredArgsConstructor;
import org.example.back_end.dto.PlaceOrderDTO;
import org.example.back_end.entity.PlaceOrder;
import org.example.back_end.service.custom.PlaceOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
@CrossOrigin
public class PlaceOrderController {

    private final PlaceOrderService placeOrderService;

    @PostMapping
    public void saveOrder(@RequestBody PlaceOrderDTO dto){
        placeOrderService.saveOrder(dto);
    }

    @PutMapping
    public void updateOrder(@RequestBody PlaceOrderDTO dto){
        placeOrderService.updateOrder(dto);
    }

    @GetMapping
    public List<PlaceOrder> getOrders(){
        return placeOrderService.getOrderData();
    }
    @DeleteMapping
    public void deleteOrder(@RequestBody PlaceOrderDTO dto){
        placeOrderService.deleteOrderById(dto.getOrderId());
    }

}
