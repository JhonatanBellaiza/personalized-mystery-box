package edu.miu.custommysterybox.model;

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
@DiscriminatorValue("CUSTOMER")
public class Customer extends User{

    @ElementCollection
    private List<String> favoriteColors;
    private String topSize;
    private String bottomSize;
    @ElementCollection
    private List<String> stylePreferences;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    public void addOrder(Order order) {
        this.orders.add(order);
    }


}
