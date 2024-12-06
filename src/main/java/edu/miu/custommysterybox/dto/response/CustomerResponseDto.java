package edu.miu.custommysterybox.dto.response;

import edu.miu.custommysterybox.model.SubscriptionType;

import java.util.List;

public record CustomerResponseDto(

        Long userId,
        String username,
        String email,
        List<String> favoriteColors,
        String topSize,
        String bottomSize,
        List<String> stylePreferences,
        SubscriptionType subscriptionType



) {
}
