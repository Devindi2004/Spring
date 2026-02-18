package org.example.back_end.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrder {
    @Id
    private String orderId;

    // Godak orders ekama customer kenekuta ayithi wenna puluwan
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "cId", nullable = false)
    private Customer customer;

    // Godak orders wala ekama item eka thiyenna puluwan
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "itemId", nullable = false)
    private Item item;

    private Integer orderQty;
    private Double orderPrice;
}