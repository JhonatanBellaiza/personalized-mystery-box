package edu.miu.custommysterybox.repository;

import edu.miu.custommysterybox.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
