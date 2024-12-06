package edu.miu.custommysterybox.controller;

import edu.miu.custommysterybox.dto.request.GetCustomerSubscriptionRequestDto;
import edu.miu.custommysterybox.dto.request.UpdateCustomerSubscriptionRequestDto;
import edu.miu.custommysterybox.dto.response.CustomerSubscriptionResponseDto;
import edu.miu.custommysterybox.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    /**
     * Endpoint to get customer subscription details.
     *
     * @param requestDto Request DTO containing the email of the customer
     * @return ResponseEntity with the response DTO or a 404 status if the customer is not found
     */
    @PostMapping
    public ResponseEntity<CustomerSubscriptionResponseDto> getCustomerSubscription(
            @RequestBody GetCustomerSubscriptionRequestDto requestDto) {

        Optional<CustomerSubscriptionResponseDto> subscriptionResponse =
                subscriptionService.getCustomerSubscription(requestDto);

        return subscriptionResponse.map(response ->
                        new ResponseEntity<>(response, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint to update customer subscription type.
     *
     * @param requestDto Request DTO containing the email and new subscription type
     * @return ResponseEntity with the response DTO or a 404 status if the customer is not found
     */
    @PatchMapping("/update-subscription")
    public ResponseEntity<CustomerSubscriptionResponseDto> updateCustomerSubscription(
            @RequestBody UpdateCustomerSubscriptionRequestDto requestDto) {

        Optional<CustomerSubscriptionResponseDto> updatedSubscription =
                subscriptionService.updateCustomerSubscription(requestDto);

        return updatedSubscription.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
