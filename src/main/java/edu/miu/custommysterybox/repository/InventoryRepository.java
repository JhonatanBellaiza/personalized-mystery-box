package edu.miu.custommysterybox.repository;

import edu.miu.custommysterybox.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);
    Optional<Item> findByColor(String color);
    Optional<List<Item>> findAllByColor(String color);
    Optional<List<Item>> findAllByNameContainsIgnoreCase(String name);
}
