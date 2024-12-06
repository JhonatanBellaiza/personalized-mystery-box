package edu.miu.custommysterybox.service.impl;

import edu.miu.custommysterybox.dto.request.GetCustomerSubscriptionRequestDto;
import edu.miu.custommysterybox.dto.request.UpdateCustomerSubscriptionRequestDto;
import edu.miu.custommysterybox.dto.response.CustomerSubscriptionResponseDto;
import edu.miu.custommysterybox.model.Customer;
import edu.miu.custommysterybox.repository.CustomerRepository;
import edu.miu.custommysterybox.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final CustomerRepository customerRepository;


    @Override
    public Optional<CustomerSubscriptionResponseDto> getCustomerSubscription(GetCustomerSubscriptionRequestDto requestDto) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(requestDto.email());

        if (customerOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found with ID: " + requestDto.email());
        }

        Customer user = customerOptional.get();

        CustomerSubscriptionResponseDto responseDto = new CustomerSubscriptionResponseDto(
                user.getUserId(),
                user.getUsername(),
                user.getSubscriptionType()
        );

        return Optional.of(responseDto);
    }


    @Override
    public Optional<CustomerSubscriptionResponseDto> updateCustomerSubscription(UpdateCustomerSubscriptionRequestDto request) {
        // Find the customer by email
        Optional<Customer> existingCustomer = customerRepository.findByEmail(request.email());

        if (existingCustomer.isEmpty()) {
            // Return empty Optional if the customer does not exist
            return Optional.empty();
        }

        Customer customerToUpdate = existingCustomer.get();

        // Update the subscription type if it's not null in the request
        if (request.subscriptionType() != null) {
            customerToUpdate.setSubscriptionType(request.subscriptionType());
        }

        // Save the updated customer
        Customer savedCustomer = customerRepository.save(customerToUpdate);

        // Build response DTO
        CustomerSubscriptionResponseDto responseDto = new CustomerSubscriptionResponseDto(
                savedCustomer.getUserId(),
                savedCustomer.getUsername(),
                savedCustomer.getSubscriptionType()
        );

        return Optional.of(responseDto);
    }
}
