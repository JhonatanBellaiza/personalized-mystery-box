package edu.miu.custommysterybox.dto.request;

import edu.miu.custommysterybox.model.SubscriptionType;

import java.util.List;

public record CustomerUpdateRequestDto(
        List<String> favoriteColors,
        String topSize,
        String bottomSize,
        List<String> stylePreferences,
        SubscriptionType subscriptionType

) {
}
