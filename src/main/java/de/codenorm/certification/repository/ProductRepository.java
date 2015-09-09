package de.codenorm.certification.repository;

import de.codenorm.certification.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mahmut.can on 09.09.2015.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
