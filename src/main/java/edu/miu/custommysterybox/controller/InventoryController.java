package edu.miu.custommysterybox.controller;

import edu.miu.custommysterybox.dto.request.ItemRequestDto;
import edu.miu.custommysterybox.dto.response.ItemResponseDto;
import edu.miu.custommysterybox.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manager/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/add-item")
    public ResponseEntity<ItemResponseDto> createItem(
            @Valid
            @RequestBody
            ItemRequestDto itemRequestDto
    ) {
        Optional<ItemResponseDto> itemResponseDto = inventoryService.createItem(itemRequestDto);
        return itemResponseDto.map(responseDto -> ResponseEntity.status(HttpStatus.CREATED).body(
                responseDto
        )).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @PostMapping("/add-items")
    public ResponseEntity<List<ItemResponseDto>> createAllItems(
            @RequestBody
            List<ItemRequestDto> itemRequestDtos
    ) {
        Optional<List<ItemResponseDto>> itemResponseDtos = inventoryService.createAllitems(itemRequestDtos);

        return itemResponseDtos.map(responseDtos -> ResponseEntity.status(HttpStatus.CREATED).body(
                responseDtos
        )).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getAllItems() {
        Optional<List<ItemResponseDto>> itemResponseDtos = inventoryService.getAllItems();

        return itemResponseDtos.map(responseDtos -> ResponseEntity.ok(responseDtos))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }



}
