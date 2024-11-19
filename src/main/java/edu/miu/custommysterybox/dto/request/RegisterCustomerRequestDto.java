package edu.miu.custommysterybox.dto.request;

import edu.miu.custommysterybox.model.SubscriptionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RegisterCustomerRequestDto(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,
        @NotBlank(message = "favoriteColors is required")
        List<String> favoriteColors,
        @NotBlank(message = "dislikedColors is required")
        List<String> dislikedColors,
        @NotBlank(message = "topSize is required")
        String topSize,
        @NotBlank(message = "bottomSize is required")
        String bottomSize,
        @NotBlank(message = "stylePreferences is required")
        List<String> stylePreferences,
        @NotBlank(message = "subscriptionType is required")
        SubscriptionType subscriptionType
) {
}
