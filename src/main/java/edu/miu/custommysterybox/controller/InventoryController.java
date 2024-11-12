package edu.miu.custommysterybox.controller;

import edu.miu.custommysterybox.dto.request.ItemRequestDto;
import edu.miu.custommysterybox.dto.response.ItemResponseDto;
import edu.miu.custommysterybox.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if (itemResponseDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    itemResponseDto.get()
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }




}
