package org.example.back_end.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    private String itemId;
    private String itemName;
    private int itemQty;
    private double itemPrice;

    // Ekama item eka orders godaka thiyenna puluwan
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PlaceOrder> orders;
}