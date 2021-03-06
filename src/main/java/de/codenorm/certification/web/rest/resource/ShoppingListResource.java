package de.codenorm.certification.web.rest.resource;

import de.codenorm.certification.domain.Product;
import de.codenorm.certification.domain.ShoppingItem;
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





    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new LocaleDateTimeEditor("yyyy-MM-dd", false));
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
