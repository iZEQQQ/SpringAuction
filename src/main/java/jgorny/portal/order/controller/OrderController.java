package jgorny.portal.order.controller;

import jgorny.portal.order.controller.model.GetOrderResponse;
import jgorny.portal.order.controller.model.GetOrdersResponse;
import jgorny.portal.order.controller.model.OrderItemRequest;
import jgorny.portal.order.controller.model.PostOrderRequest;
import jgorny.portal.order.repository.model.Order;
import jgorny.portal.order.service.OrderService;
import jgorny.portal.user.repository.model.User;
import jgorny.portal.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/{login}/orders")
public class OrderController {

    private UserService userService;

    private OrderService orderService;

    @Autowired
    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("{orderId}")
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable("login") String login, @PathVariable("orderId") Long orderId) {
        Optional<User> user = userService.findUser(login);
        if (user.isPresent()) {
            Optional<Order> order = orderService.findOrder(orderId);
            return order.map(value -> ResponseEntity.ok(new GetOrderResponse(value.getId(), value.getLocalDateTime())))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<GetOrdersResponse> getOrders(@PathVariable("login") String login) {
        Optional<User> user = userService.findUser(login);
        return user.map(value -> ResponseEntity.ok(new GetOrdersResponse(orderService.findAllIds(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Void> postOrder(@PathVariable("login") String login, @RequestBody PostOrderRequest request) {
        Optional<User> user = userService.findUser(login);
        if (user.isPresent()) {
            Order order = new Order(null, user.get());
            orderService.createOrder(login, request.getItems().stream().collect(Collectors.toMap(OrderItemRequest::getAuctionId, OrderItemRequest::getQuantity)));
            return ResponseEntity.created(URI.create("/api/users/" + login + "/orders/" + order.getId())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("login") String login, @PathVariable("orderId") Long orderId) {
        Optional<User> user = userService.findUser(login);
        if (user.isPresent()) {
            Optional<Order> order = orderService.findOrder(orderId);
            if (order.isPresent()) {
                orderService.deleteOrder(order.get());
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
