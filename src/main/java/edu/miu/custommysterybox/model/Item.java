package edu.miu.custommysterybox.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Double price;  // Price of the item

    private String color;  // Color of the item (e.g., red, blue)

    @Enumerated(EnumType.STRING)
    private ItemType type;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Item(String name, String description, Double price, String color, ItemType type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.color = color;
        this.type = type;
    }
}
