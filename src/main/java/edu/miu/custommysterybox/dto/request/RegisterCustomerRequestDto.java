package edu.miu.custommysterybox.dto.request;

import edu.miu.custommysterybox.model.Address;
import edu.miu.custommysterybox.model.SubscriptionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RegisterCustomerRequestDto(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "email is required")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,
        @NotBlank(message = "Address is required")
        Address address
) {
}
