package edu.miu.custommysterybox.dto.request;

import edu.miu.custommysterybox.model.Customer;
import edu.miu.custommysterybox.model.Item;
import edu.miu.custommysterybox.model.ItemType;
import edu.miu.custommysterybox.model.SubscriptionType;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record GenerateOrderRequestDto(
        @NotBlank(message = "Customer is required")
        Customer customer,
        @NotBlank(message = "Date is required")
        LocalDate localDate,
        @NotBlank(message = "field required")
        boolean isShipped,
        @NotBlank(message = "At least one Item is required")
        List<Item> items,
        @NotBlank(message = "Subscription type is required")
        SubscriptionType subscriptionType,
        @NotBlank(message = "quantity is required")
        double totalPrice
) {
}
