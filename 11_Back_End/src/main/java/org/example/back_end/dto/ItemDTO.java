package org.example.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDTO {
    private String itemId;   // String ID (e.g., I001)
    private String itemName;
    private int itemQty;
    private double itemPrice;
}