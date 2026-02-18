package org.example.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlaceOrderDTO {
    private String orderId;
    private String customerId; // Customer entity eke ID eka
    private String itemId;     // Item entity eke ID eka
    private Integer orderQty;
    private Double orderPrice; // Total price eka
}