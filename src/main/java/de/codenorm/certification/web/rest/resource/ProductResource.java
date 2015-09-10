package de.codenorm.certification.web.rest.resource;

import com.google.common.collect.Lists;
import de.codenorm.certification.domain.Product;
import de.codenorm.certification.repository.ProductRepository;
import de.codenorm.certification.service.ProductService;
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
    private ProductService productService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new LocaleDateTimeEditor("yyyy-MM-dd", false));
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Product> findAll() {
        return productService.findAll();
    }


    @RequestMapping(method = RequestMethod.POST)
    public Product save(@RequestBody String name) {
       return productService.save(name);

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Product update(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.update(id,product);

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable("id") Long id) {
        return  productService.findOne(id);
    }

}
