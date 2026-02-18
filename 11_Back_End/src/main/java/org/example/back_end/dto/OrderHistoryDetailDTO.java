package org.example.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryDetailDTO {

    private String itemName;
    private int qty;
    private double unitPrice;
}
