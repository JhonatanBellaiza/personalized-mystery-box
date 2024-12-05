package edu.miu.custommysterybox.service.impl;

import edu.miu.custommysterybox.dto.request.GenerateOrderRequestDto;
import edu.miu.custommysterybox.dto.response.GenerateOrderResponseDto;
import edu.miu.custommysterybox.dto.response.ItemResponseDto;
import edu.miu.custommysterybox.model.Customer;
import edu.miu.custommysterybox.model.Item;
import edu.miu.custommysterybox.model.ItemType;
import edu.miu.custommysterybox.model.Order;
import edu.miu.custommysterybox.repository.CustomerRepository;
import edu.miu.custommysterybox.repository.InventoryRepository;
import edu.miu.custommysterybox.repository.OrderRepository;
import edu.miu.custommysterybox.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final InventoryRepository inventoryRepository;
    @Override
    public Optional<GenerateOrderResponseDto> generateOrder(GenerateOrderRequestDto requestDto) {
        // 1. Validate Input
        if (requestDto == null || requestDto.email() == null) {
            throw new IllegalArgumentException("Invalid request data");
        }

        // 2. Fetch Customer
        Optional<Customer> optCustomer = customerRepository.findByEmail(requestDto.email());
        if (optCustomer.isEmpty()) {
            throw new IllegalArgumentException("Customer not found");
        }
        Customer customer = optCustomer.get();

        // 3. Fetch Customer's Previous Orders and Items
        List<Item> previousItems = customer.getOrders() == null
                ? List.of() // If the user has no orders, use an empty list
                : customer.getOrders().stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.toList());

        // 4. Fetch Items from Inventory
        List<Item> allItems = inventoryRepository.findAll();

        // 5. Filter Items Based on Preferences and Previous Orders
        List<Item> filteredItems = allItems.stream()
                .filter(item -> !previousItems.contains(item)) // Exclude previously ordered items
                .filter(item -> customer.getFavoriteColors().contains(item.getColor()) ||
                        customer.getStylePreferences().contains(item.getStyle()))
                .collect(Collectors.toList());

        if (filteredItems.isEmpty()) {
            throw new IllegalArgumentException("No suitable items available for the order");
        }

        // 6. Select Items Based on Subscription Type
        List<Item> selectedItems;
        switch (customer.getSubscriptionType()) {
            case UPPER_BODY_BOX:
                selectedItems = filteredItems.stream()
                        .filter(item -> item.getType() == ItemType.TOP)
                        .limit(1)
                        .collect(Collectors.toList());
                break;
            case LOWER_BODY_BOX:
                selectedItems = filteredItems.stream()
                        .filter(item -> item.getType() == ItemType.BOTTOM)
                        .limit(1)
                        .collect(Collectors.toList());
                break;
            case FULL_COMBO_BOX:
                Item top = filteredItems.stream()
                        .filter(item -> item.getType() == ItemType.TOP)
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("No TOP item available"));
                Item bottom = filteredItems.stream()
                        .filter(item -> item.getType() == ItemType.BOTTOM)
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("No BOTTOM item available"));
                Item accessory = filteredItems.stream()
                        .filter(item -> item.getType() == ItemType.ACCESSORY)
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("No ACCESSORY item available"));
                selectedItems = List.of(top, bottom, accessory);
                break;
            default:
                throw new IllegalArgumentException("Invalid subscription type");
        }

        // 7. Create Order Entity
        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder.setOrderDate(requestDto.localDate());
        newOrder.setShipped(requestDto.isShipped());
        for(Item item : selectedItems) {
            newOrder.addItem(item);
        }
        newOrder.setSubscriptionType(customer.getSubscriptionType());
        newOrder.setTotalPrice(calculateTotalPrice(selectedItems));

        // 8. Persist Order
        orderRepository.save(newOrder);

        // Update Customer's Orders

//        if (customer.getOrders() == null) {
//            customer.setOrders(new ArrayList<>());
//        }
        customer.addOrder(newOrder);
        customerRepository.save(customer);

        // 9. Generate ItemResponseDto
        List<ItemResponseDto> itemResponseDtos = selectedItems.stream()
                .map(item -> new ItemResponseDto(
                        item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getPrice(),
                        item.getColor(),
                        item.getType(),
                        item.getQuantity(),
                        item.getStyle()))
                .collect(Collectors.toList());

        // 10. Create Response DTO
        GenerateOrderResponseDto responseDto = new GenerateOrderResponseDto(
                customer.getUsername(),
                newOrder.getOrderDate(),
                newOrder.isShipped(),
                itemResponseDtos,
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
