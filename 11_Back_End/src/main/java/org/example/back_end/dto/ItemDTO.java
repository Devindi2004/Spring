package org.example.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data // Getter, Setter, ToString okkoma meken enawa
public class ItemDTO {
    private int itemId; // Changed to int
    private String itemName;
    private int itemQty;
    private double itemPrice;
}