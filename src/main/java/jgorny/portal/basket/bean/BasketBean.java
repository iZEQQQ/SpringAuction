package jgorny.portal.basket.bean;

import jgorny.portal.basket.bean.model.BasketItem;
import jgorny.portal.order.repository.model.OrderItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Scope("session")
public class BasketBean {

    private List<BasketItem> basketBean = new ArrayList<>();

    public List<BasketItem> findAll() {
        return Collections.unmodifiableList(basketBean);
    }

    public void delete(BasketItem basketItem) {
        basketBean.remove(basketItem);
    }

    public void deleteById(int id) {
        basketBean.remove(id);
    }

    public BasketItem save(BasketItem basketItem) {
        Optional<BasketItem> optionalItem = basketBean.stream().filter(item ->
                item.getAuction().equals(basketItem.getAuction())).findFirst();
        if (optionalItem.isPresent()) {
            optionalItem.get().setQuantity(basketItem.getQuantity());
            return optionalItem.get();
        } else {
            basketBean.add(basketItem);
            return basketItem;
        }
    }


}
