package com.rsoi.cutea.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5000)
    private String bill;
    private Float orderPrice;
    private LocalDateTime dateOfCreated;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;

    @PrePersist
    private void onCreate() { dateOfCreated = LocalDateTime.now(); }

    public static Order create(List<Product> products) {
        if (products == null || products.size() == 0) return null;

        Order newOrder = new Order();
        StringBuilder newOrderBill = new StringBuilder();
        float orderPrice = 0.0f;
        for (var p : products) {
            newOrderBill.append(String.format("%s \"%s\"\n", p.getCategory(), p.getTitle()));
            orderPrice += p.getPrice();
        }
        newOrder.setBill(newOrderBill.toString());
        newOrder.setOrderPrice(orderPrice);

        return newOrder;
    }
}
