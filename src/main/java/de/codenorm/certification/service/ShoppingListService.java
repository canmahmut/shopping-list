package de.codenorm.certification.service;

import de.codenorm.certification.domain.Product;
import de.codenorm.certification.domain.ShoppingItem;
import de.codenorm.certification.domain.ShoppingList;
import de.codenorm.certification.repository.ShoppingListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by mahmut.can on 09.09.2015.
 */

@Service
@Transactional
public class ShoppingListService {

    @Inject
    private ShoppingListRepository shoppingListRepository;

    public ShoppingItem save(Long id, Product product) {
        ShoppingList shoppingList = shoppingListRepository.findOne(id);
        ShoppingItem e = new ShoppingItem();
        e.setDone(false);
        e.setProduct(product);
        e.setStock(1);
        shoppingList.getSelectedItems().add(e);
        return e;
    }

    public ShoppingItem update(Long id, Long itemId, ShoppingItem shoppingItem) {
        ShoppingList shoppingList = shoppingListRepository.findOne(id);
        shoppingList.getSelectedItems().stream().filter(a -> a.getId() == itemId).forEach(a -> {
            a.setStock(shoppingItem.getStock());
            a.setDone(shoppingItem.isDone());
        });
//        shoppingListRepository.saveAndFlush(shoppingList);
        return shoppingItem;
    }
}
