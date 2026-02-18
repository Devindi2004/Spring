package org.example.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.back_end.dto.OrderDetailDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderDTO {
    private int orderId;
    private int customerId;
    private List<OrderDetailDTO> items;
}