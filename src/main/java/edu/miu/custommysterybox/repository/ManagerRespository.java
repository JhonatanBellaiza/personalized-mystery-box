package edu.miu.custommysterybox.repository;

import edu.miu.custommysterybox.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRespository extends JpaRepository<Manager, Long> {
}
