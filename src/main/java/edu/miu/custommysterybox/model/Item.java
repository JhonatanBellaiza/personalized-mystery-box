package edu.miu.custommysterybox.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;   // Name of the clothing item (e.g., T-shirt, Jeans)

    private String description;  // Description of the item (e.g., size, fabric)

    private double price;  // Price of the item

    private String color;  // Color of the item (e.g., red, blue)

    @Enumerated(EnumType.STRING)
    private ItemType type;

    @Enumerated(EnumType.STRING)
    private StyleType style;

    private int quantity;

    @ManyToMany(mappedBy = "items")
    private List<Order> order;

    public Item(String name, String description, double price, String color, ItemType type, int quantity, StyleType styleType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.color = color;
        this.type = type;
        this.quantity=quantity;
        this.style = styleType;
    }
}
