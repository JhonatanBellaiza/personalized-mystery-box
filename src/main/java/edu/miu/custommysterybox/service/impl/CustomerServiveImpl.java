package edu.miu.custommysterybox.service.impl;

import edu.miu.custommysterybox.dto.request.PurchaseMembershipRequestDto;
import edu.miu.custommysterybox.dto.response.PurchaseMembershipResponseDto;
import edu.miu.custommysterybox.model.Customer;
import edu.miu.custommysterybox.repository.CustomerRepository;
import edu.miu.custommysterybox.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiveImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Optional<PurchaseMembershipResponseDto> updateCustomerPreferences(PurchaseMembershipRequestDto purchaseMembershipRequestDto) {

        Optional<Customer> existingCustomer = customerRepository.findByUsername(purchaseMembershipRequestDto.username());

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
}
