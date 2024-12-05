package edu.miu.custommysterybox.service.impl;

import edu.miu.custommysterybox.dto.request.PurchaseMembershipRequestDto;
import edu.miu.custommysterybox.dto.response.PurchaseMembershipResponseDto;
import edu.miu.custommysterybox.model.Customer;
import edu.miu.custommysterybox.repository.CustomerRepository;
import edu.miu.custommysterybox.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Optional<PurchaseMembershipResponseDto> chargeCustomerPreferences(PurchaseMembershipRequestDto purchaseMembershipRequestDto) {

        Optional<Customer> existingCustomer = customerRepository.findByEmail(purchaseMembershipRequestDto.email());

        if(existingCustomer.isEmpty()) {
            return Optional.empty();
        }

        Customer updatedCustomer = existingCustomer.get();

        updatedCustomer.setFavoriteColors(purchaseMembershipRequestDto.favoriteColors());
        updatedCustomer.setTopSize(purchaseMembershipRequestDto.topSize());
        updatedCustomer.setBottomSize(purchaseMembershipRequestDto.bottomSize());
        updatedCustomer.setStylePreferences(purchaseMembershipRequestDto.stylePreferences());
        updatedCustomer.setSubscriptionType(purchaseMembershipRequestDto.subscriptionType());

        //Save Customer

        Customer savedCustomer = customerRepository.save(updatedCustomer);

        //Build response
        PurchaseMembershipResponseDto responseDto = new PurchaseMembershipResponseDto(
                savedCustomer.getUserId(), savedCustomer.getUsername(), savedCustomer.getFavoriteColors(),
                savedCustomer.getTopSize(), savedCustomer.getBottomSize(), savedCustomer.getStylePreferences(),
                savedCustomer.getSubscriptionType()
        );



        return Optional.of(responseDto);
    }

    @Override
    public Optional<PurchaseMembershipResponseDto> updateCustomerPreferences(PurchaseMembershipRequestDto purchaseMembershipRequestDto) {

        // Find the customer by username
        Optional<Customer> existingCustomer = customerRepository.findByEmail(purchaseMembershipRequestDto.email());

        if (existingCustomer.isEmpty()) {
            // Return empty Optional if the customer does not exist
            return Optional.empty();
        }

        Customer customerToUpdate = getCustomer(purchaseMembershipRequestDto, existingCustomer);

        // Save the updated customer
        Customer savedCustomer = customerRepository.save(customerToUpdate);

        // Build response DTO
        PurchaseMembershipResponseDto responseDto = new PurchaseMembershipResponseDto(
                savedCustomer.getUserId(), savedCustomer.getUsername(), savedCustomer.getFavoriteColors(),
                savedCustomer.getTopSize(), savedCustomer.getBottomSize(), savedCustomer.getStylePreferences(),
                savedCustomer.getSubscriptionType()
        );

        return Optional.of(responseDto);
    }

    private static Customer getCustomer(PurchaseMembershipRequestDto purchaseMembershipRequestDto, Optional<Customer> existingCustomer) {
        Customer customerToUpdate = existingCustomer.get();

        // Update fields only if they are not null
        if (purchaseMembershipRequestDto.favoriteColors() != null) {
            customerToUpdate.setFavoriteColors(purchaseMembershipRequestDto.favoriteColors());
        }
        if (purchaseMembershipRequestDto.topSize() != null) {
            customerToUpdate.setTopSize(purchaseMembershipRequestDto.topSize());
        }
        if (purchaseMembershipRequestDto.bottomSize() != null) {
            customerToUpdate.setBottomSize(purchaseMembershipRequestDto.bottomSize());
        }
        if (purchaseMembershipRequestDto.stylePreferences() != null) {
            customerToUpdate.setStylePreferences(purchaseMembershipRequestDto.stylePreferences());
        }
        if (purchaseMembershipRequestDto.subscriptionType() != null) {
            customerToUpdate.setSubscriptionType(purchaseMembershipRequestDto.subscriptionType());
        }
        return customerToUpdate;
    }
}
