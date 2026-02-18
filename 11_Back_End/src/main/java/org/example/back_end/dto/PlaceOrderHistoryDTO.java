package org.example.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.back_end.dto.OrderHistoryDetailDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderHistoryDTO {

    private int orderId;
    private String date;
    private String customerName;
    private List<OrderHistoryDetailDTO> items;

    private double totalPrice;
}
