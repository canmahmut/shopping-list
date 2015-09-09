package de.codenorm.certification.web.rest.resource;

import de.codenorm.certification.domain.Product;
import de.codenorm.certification.domain.ShoppingItem;
import de.codenorm.certification.domain.ShoppingList;
import de.codenorm.certification.repository.ShoppingListRepository;
import de.codenorm.certification.service.ShoppingListService;
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
@RequestMapping("/api/shoppingList")
public class ShoppingListResource {


    @Inject
    private ShoppingListRepository shoppingListRepository;

    @Inject
    private ShoppingListService shoppingListService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new LocaleDateTimeEditor("yyyy-MM-dd", false));
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<ShoppingList> findAll() {
        return shoppingListRepository.findAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ShoppingItem saveShoppingItem(@PathVariable("id") Long id, @RequestBody Product product) {
        return shoppingListService.save(id, product);
    }

    @RequestMapping(value = "/{id}/{itemId}", method = RequestMethod.PUT)
    public ShoppingItem saveShoppingItem(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId, @RequestBody ShoppingItem product) {
        return shoppingListService.update(id, itemId, product);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ShoppingList findById(@PathVariable("id") Long id) {
        return shoppingListRepository.findOne(id);
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
