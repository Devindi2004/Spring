package org.example.back_end.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String itemName;
    private int itemQty;
    private double itemPrice;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orders;
}