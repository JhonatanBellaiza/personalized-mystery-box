package edu.miu.custommysterybox.service.impl;

import edu.miu.custommysterybox.dto.request.GenerateOrderRequestDto;
import edu.miu.custommysterybox.dto.response.GenerateOrderResponseDto;
import edu.miu.custommysterybox.model.Customer;
import edu.miu.custommysterybox.model.Item;
import edu.miu.custommysterybox.model.ItemType;
import edu.miu.custommysterybox.model.Order;
import edu.miu.custommysterybox.repository.CustomerRepository;
import edu.miu.custommysterybox.repository.OrderRepository;
import edu.miu.custommysterybox.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Override
    public Optional<GenerateOrderResponseDto> generateOrderResponseDto(GenerateOrderRequestDto requestDto) {
        // 1. Validate Input
        if (requestDto == null || requestDto.customer() == null || requestDto.items() == null) {
            throw new IllegalArgumentException("Invalid request data");
        }

        // 2. Fetch Customer
        Customer customer = requestDto.customer();
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        // 3. Fetch or Initialize Orders
        List<Order> customerOrders = customer.getOrders() != null ? customer.getOrders() : List.of();

        // 4. Filter Available Items
        List<Item> previousItems = customerOrders.stream()
                .filter(order -> order.getSubscriptionType() == requestDto.subscriptionType())
                .flatMap(order -> order.getItems().stream())
                .toList();

        List<Item> availableItems = requestDto.items().stream()
                .filter(item -> !previousItems.contains(item))
                .toList();

        if (availableItems.isEmpty()) {
            throw new IllegalArgumentException("No items available for this order based on past shipments");
        }

        // 5. Generate Items Based on Subscription Type
        List<Item> selectedItems = availableItems.stream()
                .filter(item -> {
                    switch (requestDto.subscriptionType()) {
                        case UPPER_BODY_BOX:
                            return item.getType() == ItemType.TOP;
                        case LOWER_BODY_BOX:
                            return item.getType() == ItemType.BOTTOM;
                        case FULL_COMBO_BOX:
                            return true; // Include all items for full combo
                        default:
                            return false;
                    }
                })
                .toList();

        // 6. Create Order Entity
        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder.setOrderDate(requestDto.localDate());
        newOrder.setShipped(requestDto.isShipped());
        newOrder.setItems(selectedItems);
        newOrder.setSubscriptionType(requestDto.subscriptionType());
        newOrder.setTotalPrice(calculateTotalPrice(selectedItems));

        // 7. Persist Order (assumes you have a repository)
        orderRepository.save(newOrder);

        // Add the new order to the customer's orders list and persist the customer
        customerOrders.add(newOrder);
        customer.setOrders(customerOrders);
        customerRepository.save(customer);

        // 8. Create Response DTO
        GenerateOrderResponseDto responseDto = new GenerateOrderResponseDto(
                newOrder.getCustomer(),
                newOrder.getOrderDate(),
                newOrder.isShipped(),
                newOrder.getItems(),
                newOrder.getSubscriptionType(),
                newOrder.getTotalPrice()
        );

        return Optional.of(responseDto);
    }

    // Helper Method for Total Price Calculation
    private double calculateTotalPrice(List<Item> items) {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }
}
