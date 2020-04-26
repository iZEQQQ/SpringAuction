package jgorny.portal.order.service;

import jgorny.portal.auction.repository.AuctionRepository;
import jgorny.portal.auction.repository.model.Auction;
import jgorny.portal.order.repository.OrderRepository;
import jgorny.portal.order.repository.model.Order;
import jgorny.portal.order.repository.model.OrderItem;
import jgorny.portal.user.repository.UserRepository;
import jgorny.portal.user.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private UserRepository userRepository;

    private AuctionRepository auctionRepository;

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, AuctionRepository auctionRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.auctionRepository = auctionRepository;
    }

    public List<Long> findAllIds(User user) {
        return orderRepository.findId(user);
    }

    public Optional<Order> findOrder(Long id) {
        return orderRepository.findById(id);
    }

    public void createOrder(Order order) {
        order.setLocalDateTime(LocalDateTime.now());
        orderRepository.save(order);
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    @Transactional
    public void createOrder(String login, Map<Long, Integer> items) {
        Order order = new Order();
        order.setItems(new ArrayList<>());
        Optional<User> user = userRepository.findById(login);
        if (user.isPresent()) {
            order.setUser(user.get());
        } else {
            throw new IllegalArgumentException("No such user");
        }

        items.forEach((auctionId, quantity) -> {
            Optional<Auction> auction = auctionRepository.findById(auctionId);
            if (auction.isPresent()) {
                if (auction.get().getQuantity() >= quantity) {
                    auction.get().setQuantity(auction.get().getQuantity() - quantity);
                    order.getItems().add(new OrderItem(order, auction.get(), quantity));
                } else {
                    throw new IllegalArgumentException("No enough items");
                }
            } else {
                throw new IllegalArgumentException("No such auction");
            }
        });
        order.setLocalDateTime(LocalDateTime.now());
        orderRepository.save(order);

    }
}
