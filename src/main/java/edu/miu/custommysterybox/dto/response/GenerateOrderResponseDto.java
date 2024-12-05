package edu.miu.custommysterybox.dto.response;

import edu.miu.custommysterybox.model.Customer;
import edu.miu.custommysterybox.model.Item;
import edu.miu.custommysterybox.model.SubscriptionType;

import java.time.LocalDate;
import java.util.List;

public record GenerateOrderResponseDto(
        String username,
        LocalDate localDate,
        boolean isShipped,
        List<ItemResponseDto> items,
        SubscriptionType subscriptionType,
        double totalPrice
) {
}