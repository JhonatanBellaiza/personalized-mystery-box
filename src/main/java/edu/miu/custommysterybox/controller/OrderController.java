package edu.miu.custommysterybox.controller;

import edu.miu.custommysterybox.dto.request.GenerateOrderRequestDto;
import edu.miu.custommysterybox.dto.response.GenerateOrderResponseDto;
import edu.miu.custommysterybox.service.OrderService;
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
@RequestMapping("/customer/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * Endpoint to generate an order for a customer.
     *
     * @param requestDto The request DTO containing order generation details.
     * @return ResponseEntity containing the generated order response DTO.
     */
    @PostMapping("/generate")
    public ResponseEntity<GenerateOrderResponseDto> generateOrder(
            @RequestBody GenerateOrderRequestDto requestDto) {

        // Call the service method to generate the order
        Optional<GenerateOrderResponseDto> generatedOrder =
                orderService.generateOrder(requestDto);

        return generatedOrder.map(orderResponseDto ->
                        new ResponseEntity<>(orderResponseDto, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST)); // Return 400 if order could not be generated
    }
}
