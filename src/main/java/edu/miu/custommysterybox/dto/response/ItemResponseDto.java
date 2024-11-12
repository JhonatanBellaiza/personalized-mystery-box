package edu.miu.custommysterybox.dto.response;

import edu.miu.custommysterybox.model.ItemType;

public record ItemResponseDto(Long id, String name, String description, double price, String color, ItemType type, int quantity) {
}
