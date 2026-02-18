package org.example.back_end.service.custom;

import org.example.back_end.dto.PlaceOrderDTO;
import org.example.back_end.dto.PlaceOrderHistoryDTO;

import java.util.List;

public interface PlaceOrderService {

    void saveOrder(PlaceOrderDTO dto);

    List<PlaceOrderHistoryDTO> getOrderData();  // <-- change

    void deleteOrderById(int id);

    void updateOrder(PlaceOrderDTO dto);
}
