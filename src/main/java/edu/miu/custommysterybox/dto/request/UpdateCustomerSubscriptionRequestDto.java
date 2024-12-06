package edu.miu.custommysterybox.dto.request;

import edu.miu.custommysterybox.model.SubscriptionType;
import edu.miu.custommysterybox.service.SubscriptionService;

public record UpdateCustomerSubscriptionRequestDto(
        String email,
        SubscriptionType subscriptionType
) {
}
