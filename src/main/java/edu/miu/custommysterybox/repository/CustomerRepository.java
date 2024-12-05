package edu.miu.custommysterybox.repository;

import edu.miu.custommysterybox.model.Customer;
import edu.miu.custommysterybox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByEmail(String email);

}
