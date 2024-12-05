package edu.miu.custommysterybox.dto.request;

import edu.miu.custommysterybox.model.ItemType;
import edu.miu.custommysterybox.model.StyleType;
import jakarta.validation.constraints.NotBlank;

public record ItemRequestDto(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Description is required")
        String description,
        @NotBlank(message = "price is required")
        double price,
        @NotBlank(message = "color is required")
        String color,
        @NotBlank(message = "type is required")
        ItemType type,
        @NotBlank(message = "quantity is required")
        int quantity,
        @NotBlank(message = "Style is required")
        StyleType styleType

) {
}
