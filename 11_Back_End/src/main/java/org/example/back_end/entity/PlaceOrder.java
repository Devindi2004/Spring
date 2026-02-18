package org.example.back_end.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.back_end.entity.Customer;
import org.example.back_end.entity.Item;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrder {
    @Id
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false) // Meka Item entity eke int ID ekata map wei
    private Item item;

    private Integer orderQty;
    private Double orderPrice;
}