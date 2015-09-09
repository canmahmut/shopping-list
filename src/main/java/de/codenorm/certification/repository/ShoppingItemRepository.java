package de.codenorm.certification.repository;

import de.codenorm.certification.domain.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> {


}
