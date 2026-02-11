package org.example.back_end.service.custom;

import org.example.back_end.dto.ItemDTO;
import org.example.back_end.dto.PlaceOrderDTO;
import org.example.back_end.entity.Item;
import org.example.back_end.entity.PlaceOrder;

import java.util.List;

public interface PlaceOrderService {
    public void saveOrder(PlaceOrderDTO placeOrderDTO);
    public void updateOrder(PlaceOrderDTO placeOrderDTO);

    List<PlaceOrder> getOrderData();

    void deleteOrder(PlaceOrderDTO placeOrderDTO);
}
