package edu.miu.custommysterybox.service.impl;

import edu.miu.custommysterybox.dto.request.ItemRequestDto;
import edu.miu.custommysterybox.dto.response.ItemResponseDto;
import edu.miu.custommysterybox.model.Item;
import edu.miu.custommysterybox.model.ItemType;
import edu.miu.custommysterybox.repository.InventoryRepository;
import edu.miu.custommysterybox.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public Optional<ItemResponseDto> createItem(ItemRequestDto itemRequestDto) {

        //Crate new object using the data from ItemRequestDto
        Item newItem = new Item();
        newItem.setName(itemRequestDto.name());
        newItem.setColor(itemRequestDto.color());
        newItem.setDescription((itemRequestDto.description()));
        newItem.setPrice(itemRequestDto.price());
        newItem.setType(itemRequestDto.type());
        newItem.setQuantity(itemRequestDto.quantity());

        //save item
        Item savedItem = inventoryRepository.save(newItem);

        //Construct ItemResponseDto object
        ItemResponseDto itemResponseDto = new ItemResponseDto(savedItem.getId(), savedItem.getName(),
                savedItem.getDescription(), savedItem.getPrice(), savedItem.getColor(),
                savedItem.getType(), savedItem.getQuantity());

        return Optional.of(itemResponseDto);
    }

    @Override
    public Optional<List<ItemResponseDto>> createAllitems(List<ItemRequestDto> items) {
        // Check if the input list is empty
        if (items == null || items.isEmpty()) {
            return Optional.empty();
        }

        // Create a list to hold the saved Item entities
        List<Item> newItems = new ArrayList<>();

        // Convert each ItemRequestDto to an Item and add to the list
        for (ItemRequestDto itemRequestDto : items) {
            Item newItem = new Item();
            newItem.setName(itemRequestDto.name());
            newItem.setColor(itemRequestDto.color());
            newItem.setDescription(itemRequestDto.description());
            newItem.setPrice(itemRequestDto.price());
            newItem.setType(itemRequestDto.type());
            newItem.setQuantity(itemRequestDto.quantity());

            newItems.add(newItem);
        }

        // Save all items in bulk
        List<Item> savedItems = inventoryRepository.saveAll(newItems);

        // Convert the saved items into ItemResponseDto objects
        List<ItemResponseDto> responseDtos = savedItems.stream()
                .map(savedItem -> new ItemResponseDto(
                        savedItem.getId(),
                        savedItem.getName(),
                        savedItem.getDescription(),
                        savedItem.getPrice(),
                        savedItem.getColor(),
                        savedItem.getType(),
                        savedItem.getQuantity()))
                .collect(Collectors.toList());

        return Optional.of(responseDtos);
    }

    @Override
    public Optional<ItemResponseDto> updateItem(ItemRequestDto item) {
        return Optional.empty();
    }

    @Override
    public Optional<List<ItemResponseDto>> getAllItems() {

        List<Item> items = inventoryRepository.findAll();

        if (items.isEmpty()) return Optional.empty();

        List<ItemResponseDto> itemResponseDtos = items.stream()
                .map(item -> new ItemResponseDto(
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getPrice(),
                        item.getColor(),
                        item.getType(),
                        item.getQuantity()

                )).collect(Collectors.toList());

        return Optional.of(itemResponseDtos);
    }

    @Override
    public Optional<List<ItemResponseDto>> findAllItemsByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<List<ItemResponseDto>> findAllItemsByColor(String color) {
        return Optional.empty();
    }

    @Override
    public void deleteItem(Long id) {

    }
}
