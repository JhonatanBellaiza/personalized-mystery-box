package edu.miu.custommysterybox.dto.response;

import edu.miu.custommysterybox.model.SubscriptionType;

import java.util.List;

public record PurchaseMembershipResponseDto(
        Long userId,
        String username,
        List<String> favoriteColors,
        String topSize,
        String bottomSize,
        List<String> stylePreferences,
        SubscriptionType subscriptionType
    ) {
}
