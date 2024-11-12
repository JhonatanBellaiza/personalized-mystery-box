package edu.miu.custommysterybox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("CUSTOMER")
public class Customer extends User{

    @ElementCollection
    private List<String> favoriteColors;
    @ElementCollection
    private List<String> dislikedColors;
    private String preferredFit;
    @ElementCollection
    private List<String> stylePreferences;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;


}
