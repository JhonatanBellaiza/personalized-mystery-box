package edu.miu.custommysterybox.service;

import edu.miu.custommysterybox.dto.request.ItemRequestDto;
import edu.miu.custommysterybox.dto.response.ItemResponseDto;
import edu.miu.custommysterybox.model.Item;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    Optional<ItemResponseDto> createItem(ItemRequestDto item);
    Optional<List<ItemResponseDto>> createAllitems(List<ItemRequestDto> items);
    Optional<ItemResponseDto> updateItem(ItemRequestDto item);
    Optional<List<ItemResponseDto>> getAllItems();
    Optional<List<ItemResponseDto>> findAllItemsByName(String name);

    Optional<List<ItemResponseDto>> findAllItemsByColor(String color);

    void deleteItem(Long id);
}
