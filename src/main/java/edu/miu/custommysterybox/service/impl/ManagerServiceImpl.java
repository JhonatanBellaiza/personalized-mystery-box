package edu.miu.custommysterybox.service.impl;

import edu.miu.custommysterybox.dto.request.CustomerUpdateRequestDto;
import edu.miu.custommysterybox.dto.response.CustomerResponseDto;
import edu.miu.custommysterybox.model.Customer;
import edu.miu.custommysterybox.model.SubscriptionType;
import edu.miu.custommysterybox.repository.CustomerRepository;
import edu.miu.custommysterybox.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final CustomerRepository customerRepository;

    @Override
    public Optional<List<CustomerResponseDto>> getAllUsers() {
        // Fetch all customers from the repository
        List<Customer> customers = customerRepository.findAll();

        if (customers.isEmpty()) {
            return Optional.empty();
        }

        // Map each Customer entity to CustomerResponseDto
        List<CustomerResponseDto> customerResponseDtos = customers.stream()
                .map(customer -> new CustomerResponseDto(
                        customer.getUserId(),
                        customer.getUsername(),
                        customer.getEmail(),
                        customer.getFavoriteColors(),
                        customer.getTopSize(),
                        customer.getBottomSize(),
                        customer.getStylePreferences(),
                        customer.getSubscriptionType()))
                .collect(Collectors.toList());

        return Optional.of(customerResponseDtos);
    }

    @Override
    public Optional<CustomerResponseDto> getCustomerDetails(Long id) {
        // Find customer by email
        Optional<Customer> customerOpt = customerRepository.findById(id);

        return customerOpt.map(customer -> new CustomerResponseDto(
                customer.getUserId(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getFavoriteColors(),
                customer.getTopSize(),
                customer.getBottomSize(),
                customer.getStylePreferences(),
                customer.getSubscriptionType()));
    }

    @Override
    public Optional<CustomerResponseDto> editCustomerDetails(Long id, CustomerUpdateRequestDto updateRequestDto) {
        // Find customer by email
        Optional<Customer> customerOpt = customerRepository.findById(id);

        if (customerOpt.isEmpty()) {
            return Optional.empty();
        }

        Customer existingCustomer = customerOpt.get();

        // Update customer fields based on the request DTO
        if (updateRequestDto.favoriteColors() != null) {
            existingCustomer.setFavoriteColors(updateRequestDto.favoriteColors());
        }
        if (updateRequestDto.topSize() != null) {
            existingCustomer.setTopSize(updateRequestDto.topSize());
        }
        if (updateRequestDto.bottomSize() != null) {
            existingCustomer.setBottomSize(updateRequestDto.bottomSize());
        }
        if (updateRequestDto.stylePreferences() != null) {
            existingCustomer.setStylePreferences(updateRequestDto.stylePreferences());
        }
        if (updateRequestDto.subscriptionType() != null) {
            existingCustomer.setSubscriptionType(updateRequestDto.subscriptionType());
        }

        // Save updated customer
        Customer updatedCustomer = customerRepository.save(existingCustomer);

        // Build and return response DTO
        CustomerResponseDto responseDto = new CustomerResponseDto(
                updatedCustomer.getUserId(),
                updatedCustomer.getUsername(),
                updatedCustomer.getEmail(),
                updatedCustomer.getFavoriteColors(),
                updatedCustomer.getTopSize(),
                updatedCustomer.getBottomSize(),
                updatedCustomer.getStylePreferences(),
                updatedCustomer.getSubscriptionType()
        );

        return Optional.of(responseDto);
    }
}
