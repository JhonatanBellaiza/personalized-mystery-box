package edu.miu.custommysterybox.dto.request;

import edu.miu.custommysterybox.model.ItemType;
import edu.miu.custommysterybox.model.SubscriptionType;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PurchaseMembershipRequestDto(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "At least one color is required")
        List<String> favoriteColors,
        @NotBlank(message = "Size is required")
        String topSize,
        @NotBlank(message = "Size is required")
        String bottomSize,
        @NotBlank(message = "Style preferences is required")
        List<String> stylePreferences,
        @NotBlank(message = "Subscription is required")
        SubscriptionType subscriptionType
) {
}
