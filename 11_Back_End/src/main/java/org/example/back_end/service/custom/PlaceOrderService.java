package org.example.back_end.service.custom;

import org.example.back_end.dto.PlaceOrderDTO;
import org.example.back_end.entity.PlaceOrder;

import java.util.List;

public interface PlaceOrderService {

    void saveOrder(PlaceOrderDTO dto);

    void updateOrder(PlaceOrderDTO dto);

    List<PlaceOrder> getOrderData();

    void deleteOrderById(String id);
}
