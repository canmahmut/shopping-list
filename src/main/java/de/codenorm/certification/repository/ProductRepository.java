package de.codenorm.certification.repository;

import de.codenorm.certification.domain.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mahmut.can on 09.09.2015.
 */
public interface ProductRepository extends CrudRepository<Product, Long>, JpaSpecificationExecutor<Product> {


    Product findByNameEqualsIgnoreCase(String name);
}
