package jgorny.portal.basket.bean;

import jgorny.portal.basket.bean.model.BasketItem;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BasketBean {

    private List<BasketItem> items = new ArrayList<>();

    public List<BasketItem> findAll() {
        return Collections.unmodifiableList(items);
    }

    public void delete(BasketItem basketItem) {
        items.remove(basketItem);
    }

    public void deleteById(int id) {
        items.remove(id);
    }

    public BasketItem save(BasketItem basketItem) {
        Optional<BasketItem> optionalItem = items.stream().filter(item ->
                item.getAuction().equals(basketItem.getAuction())).findFirst();
        if (optionalItem.isPresent()) {
            optionalItem.get().setQuantity(basketItem.getQuantity());
            return optionalItem.get();
        } else {
            items.add(basketItem);
            return basketItem;
        }
    }


}
