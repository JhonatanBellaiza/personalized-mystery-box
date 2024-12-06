package edu.miu.custommysterybox.controller;

import edu.miu.custommysterybox.dto.request.PurchaseMembershipRequestDto;
import edu.miu.custommysterybox.dto.response.PurchaseMembershipResponseDto;
import edu.miu.custommysterybox.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Endpoint to charge customer preferences (new purchase).
     *
     * @param purchaseMembershipRequestDto Request DTO containing preferences
     * @return ResponseEntity with the response DTO or a 404 status if the customer is not found
     */
    @PostMapping("/charge-preferences")
    public ResponseEntity<PurchaseMembershipResponseDto> chargeCustomerPreferences(
            @RequestBody PurchaseMembershipRequestDto purchaseMembershipRequestDto) {

        // Call the service method to perform the partial update
        Optional<PurchaseMembershipResponseDto> updatedCustomer =
                customerService.chargeCustomerPreferences(purchaseMembershipRequestDto);

        return updatedCustomer.map(purchaseMembershipResponseDto ->
                new ResponseEntity<>(purchaseMembershipResponseDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    /**
     * Endpoint to update customer preferences partially.
     *
     * @param purchaseMembershipRequestDto Request DTO containing preferences
     * @return ResponseEntity with the response DTO or a 404 status if the customer is not found
     */
    @PatchMapping("/update-preferences")
    public ResponseEntity<PurchaseMembershipResponseDto> updateCustomerPreferences(
            @RequestBody PurchaseMembershipRequestDto purchaseMembershipRequestDto) {

        Optional<PurchaseMembershipResponseDto> response = customerService.updateCustomerPreferences(purchaseMembershipRequestDto);

        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }



}
