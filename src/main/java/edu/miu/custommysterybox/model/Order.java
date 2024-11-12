package edu.miu.custommysterybox.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDate orderDate;
    private boolean isShipped;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Item> items;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    private Double totalPrice;
}

