package edu.miu.custommysterybox.dto.response;

import edu.miu.custommysterybox.model.SubscriptionType;

public record CustomerSubscriptionResponseDto(Long customerId, String username, SubscriptionType subscriptionType) {
}
