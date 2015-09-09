package de.codenorm.certification.web.rest.resource;

import de.codenorm.certification.domain.Product;
import de.codenorm.certification.domain.ShoppingList;
import de.codenorm.certification.repository.ProductRepository;
import de.codenorm.certification.web.propertyeditors.LocaleDateTimeEditor;
import org.joda.time.LocalDateTime;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by can on 08.09.15.
 */
@RestController
@RequestMapping("/api/product")
public class ProductResource {


    @Inject
    private ProductRepository productRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new LocaleDateTimeEditor("yyyy-MM-dd", false));
    }




    @RequestMapping(method = RequestMethod.GET)
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable("id") Long id) {
        return productRepository.findOne(id);
    }

}
