package de.codenorm.certification.service;

import de.codenorm.certification.domain.Product;
import de.codenorm.certification.domain.ShoppingItem;
import de.codenorm.certification.repository.ShoppingItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by mahmut.can on 09.09.2015.
 */

@Service
@Transactional
public class ShoppingListService {


    @Inject
    private ShoppingItemRepository shoppingItemRepository;

    public ShoppingItem save(ShoppingItem shoppingItem) {
        shoppingItem.setStock(1);
        shoppingItemRepository.save(shoppingItem);
        return shoppingItem;
    }

    public ShoppingItem update(Long id, Long itemId, ShoppingItem shoppingItem) {
//        ShoppingList shoppingList = shoppingListRepository.findOne(id);
//        shoppingList.getSelectedItems().stream().filter(a -> a.getId() == itemId).forEach(a -> {
//            a.setStock(shoppingItem.getStock());
//            a.setDone(shoppingItem.isDone());
//        });
////        shoppingListRepository.saveAndFlush(shoppingList);
//        return shoppingItem;
        return shoppingItem;
    }

    public List<ShoppingItem> findShoppingItems() {
        return shoppingItemRepository.findAll();
    }

    public ShoppingItem update(Long id, ShoppingItem shoppingItem) {
        ShoppingItem one = shoppingItemRepository.findOne(id);
        if(shoppingItem.isDone()){
            one.setDone(true);
        }else{
            one.setDone(false);
        }
        one.setStock(shoppingItem.getStock());

        if(one.getStock() == 0){
            shoppingItemRepository.delete(one.getId());
            one.setId(null);
        }

        return one;
    }

    public void delete(Long id) {
        shoppingItemRepository.delete(id);
    }
}
