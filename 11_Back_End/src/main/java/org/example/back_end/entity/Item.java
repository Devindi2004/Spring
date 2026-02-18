package org.example.back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId; // Meka INT wenna ona
    private String itemName;
    private int itemQty;
    private double itemPrice;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<PlaceOrder> orders;
}