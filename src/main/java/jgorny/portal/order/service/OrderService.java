package jgorny.portal.order.service;

import jgorny.portal.order.repository.OrderRepository;
import jgorny.portal.order.repository.model.Order;
import jgorny.portal.user.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Long> findAllIds(User user){
        return repository.findId(user);
    }

    public Optional<Order> findOrder(Long id){
        return repository.findById(id);
    }

    public void createOrder(Order order){
        order.setLocalDateTime(LocalDateTime.now());
        repository.save(order);
    }

    public void deleteOrder(Order order){
        repository.delete(order);
    }

}
