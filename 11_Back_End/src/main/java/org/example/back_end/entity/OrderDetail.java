package org.example.back_end.entity;

import com.fasterxml.jackson.annotation.JsonBackReference; // Meka import karanna
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn( nullable = false)
    @JsonBackReference // Meka nisa JSON eka ayemath Order ekata yana eka nawathinawa
    private PlaceOrder order;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Item item;

    private int qty;
    private double unitPrice;
}