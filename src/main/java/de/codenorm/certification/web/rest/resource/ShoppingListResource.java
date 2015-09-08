package de.codenorm.certification.web.rest.resource;

import de.codenorm.certification.domain.Product;
import de.codenorm.certification.domain.ShoppingItem;
import de.codenorm.certification.domain.ShoppingList;
import de.codenorm.certification.web.propertyeditors.LocaleDateTimeEditor;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.LocalDateTime;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by can on 08.09.15.
 */
@RestController
@RequestMapping("/api/shoppingList")
public class ShoppingListResource {


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new LocaleDateTimeEditor("yyyy-MM-dd", false));
    }


    int count = 4;

    List<Product> productList = new ArrayList<>(count);

    @PostConstruct
    public void init() {
        for (int i = 0; i < count; i++) {
            Product product = new Product();
            product.setId((long) RandomUtils.nextInt(40));
            product.setName(RandomStringUtils.randomAlphabetic(6));
            productList.add(product);
        }
        shoppingLists.add(new ShoppingList());
        shoppingLists.add(new ShoppingList());
        shoppingLists.add(new ShoppingList());
        shoppingLists = shoppingLists.stream().
                map(a -> {
                    a.setArchived(RandomUtils.nextBoolean());
                    a.setCreationDate(new Date());
                    a.setId((long) RandomUtils.nextInt(40));

                    int capacity = RandomUtils.nextInt(count);
                    List<ShoppingItem> selectedProductList = new ArrayList<>(capacity);

                    for (int i = 0; i < capacity; i++) {
                        ShoppingItem e = new ShoppingItem();
                        e.setDone(RandomUtils.nextBoolean());
                        e.setProduct(productList.get(i));
                        e.setStock(RandomUtils.nextInt(5));
                        selectedProductList.add(e);
                    }

                    a.setSelectedItems(selectedProductList);
                    return a;

                }).collect(Collectors.toList());

    }

    private List<ShoppingList> shoppingLists = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET)
    public List<ShoppingList> findAll() {
        return  shoppingLists;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ShoppingList findById(@PathVariable("id") Long id) {
        return shoppingLists.stream().filter(a -> a.getId().equals(id)).findFirst().get();
    }

   /* @RequestMapping(value = "/audits/byDates",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    //@RolesAllowed(AuthoritiesConstants.ADMIN)
    public List<AuditEvent> findByDates(@RequestParam(value = "fromDate") LocalDateTime fromDate,
                                        @RequestParam(value = "toDate") LocalDateTime toDate) {
        return auditEventService.findByDates(fromDate, toDate);
    }*/
}
