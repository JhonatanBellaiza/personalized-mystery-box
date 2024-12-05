package edu.miu.custommysterybox.service;

import edu.miu.custommysterybox.dto.request.PurchaseMembershipRequestDto;
import edu.miu.custommysterybox.dto.response.PurchaseMembershipResponseDto;

import java.util.Optional;

public interface CustomerService {
    Optional<PurchaseMembershipResponseDto> chargeCustomerPreferences(PurchaseMembershipRequestDto purchaseMembershipRequestDto);
    Optional<PurchaseMembershipResponseDto> updateCustomerPreferences(PurchaseMembershipRequestDto purchaseMembershipRequestDto);

}
