package edu.miu.custommysterybox.service;

import edu.miu.custommysterybox.dto.request.CustomerUpdateRequestDto;
import edu.miu.custommysterybox.dto.response.CustomerResponseDto;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    Optional<List<CustomerResponseDto>> getAllUsers();
    Optional<CustomerResponseDto> getCustomerDetails(Long id);
    Optional<CustomerResponseDto> editCustomerDetails(Long id, CustomerUpdateRequestDto updateRequestDto);
}
