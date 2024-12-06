package edu.miu.custommysterybox.service;

import edu.miu.custommysterybox.dto.request.GetCustomerSubscriptionRequestDto;
import edu.miu.custommysterybox.dto.request.UpdateCustomerSubscriptionRequestDto;
import edu.miu.custommysterybox.dto.response.CustomerSubscriptionResponseDto;

import java.util.Optional;

public interface SubscriptionService {
    Optional<CustomerSubscriptionResponseDto>  getCustomerSubscription(GetCustomerSubscriptionRequestDto requestDto);
    Optional<CustomerSubscriptionResponseDto> updateCustomerSubscription(UpdateCustomerSubscriptionRequestDto request);
}
