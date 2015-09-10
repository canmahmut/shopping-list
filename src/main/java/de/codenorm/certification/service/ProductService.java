package de.codenorm.certification.service;

import com.google.common.collect.Lists;
import de.codenorm.certification.domain.Product;
import de.codenorm.certification.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by mahmut.can on 10.09.2015.
 */
@Service
@Transactional
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    public Product update(Long id, Product product) {
        Product one = productRepository.findOne(id);
        one.setName(product.getName());
        return one;
    }

    public Product save(String name) {
        Product product = new Product();
        product.setName(name);
        Product existing = productRepository.findByNameContainingIgnoreCase(name);
        if (existing != null) {
            throw new RuntimeException("Product exits");
        } else {
            return productRepository.save(product);
        }
    }

    public List<Product> findAll() {
        return Lists.newArrayList(productRepository.findAll());
    }

    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }
}
