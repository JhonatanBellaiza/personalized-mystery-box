package edu.miu.custommysterybox.controller;

import edu.miu.custommysterybox.dto.request.PurchaseMembershipRequestDto;
import edu.miu.custommysterybox.dto.response.PurchaseMembershipResponseDto;
import edu.miu.custommysterybox.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PatchMapping("/update-preferences")
    public ResponseEntity<PurchaseMembershipResponseDto> updateCustomerPreferences(
            @RequestBody PurchaseMembershipRequestDto purchaseMembershipRequestDto) {

        // Call the service method to perform the partial update
        Optional<PurchaseMembershipResponseDto> updatedCustomer =
                customerService.updateCustomerPreferences(purchaseMembershipRequestDto);

        return updatedCustomer.map(purchaseMembershipResponseDto ->
                new ResponseEntity<>(purchaseMembershipResponseDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }


}
