package jgorny.portal.order.service;

import jgorny.portal.order.repository.OrderItemRepository;
import jgorny.portal.order.repository.OrderRepository;
import jgorny.portal.order.repository.model.Order;
import jgorny.portal.order.repository.model.OrderItem;
import jgorny.portal.user.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private OrderItemRepository repository;

    @Autowired
    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    public List<Long> findAllIds(Order order){
        return repository.findId(order);
    }

    public Optional<OrderItem> findOrder(Long id){
        return repository.findById(id);
    }

}
