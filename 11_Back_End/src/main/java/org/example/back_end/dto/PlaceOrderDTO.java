package org.example.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderDTO {
    private String orderId;
    private int customerId;
    private int itemId;     // int danna
    private Integer orderQty;
    private Double orderPrice;
}