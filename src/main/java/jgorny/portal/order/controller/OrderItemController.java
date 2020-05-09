package jgorny.portal.order.controller;

import jgorny.portal.auction.controller.model.GetAuctionResponse;
import jgorny.portal.order.controller.model.GetOrderItemResponse;
import jgorny.portal.order.controller.model.GetOrderItemsResponse;
import jgorny.portal.order.repository.model.Order;
import jgorny.portal.order.repository.model.OrderItem;
import jgorny.portal.order.service.OrderItemService;
import jgorny.portal.order.service.OrderService;
import jgorny.portal.user.repository.model.User;
import jgorny.portal.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/users/{login}/orders/{orderId}/items")
public class OrderItemController {

    private UserService userService;

    private OrderService orderService;

    private OrderItemService orderItemService;

    @Autowired
    public OrderItemController(UserService userService, OrderService orderService, OrderItemService orderItemService) {
        this.userService = userService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @GetMapping("{itemId}")
    public ResponseEntity<GetOrderItemResponse> getItem(@PathVariable("itemId") Long itemId,
                                                        @PathVariable("orderId") Long orderId,
                                                        @PathVariable("login") String login) {
        Optional<User> user = userService.findUser(login);
        if (user.isPresent()) {
            Optional<Order> order = orderService.findOrder(orderId);
            if (order.isPresent()) {
                Optional<OrderItem> orderItem = orderItemService.findOrder(itemId);
                return orderItem.map(item -> ResponseEntity.ok(new GetOrderItemResponse(item.getId(), item.getQuantity()
                        , new GetAuctionResponse(item.getAuction().getId(),
                        item.getAuction().getName(),
                        item.getAuction().getPrice(),
                        item.getAuction().getQuantity())))).orElseGet(() -> ResponseEntity.notFound().build());
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<GetOrderItemsResponse> getItems(@PathVariable("orderId")Long orderId,
                                                          @PathVariable("login")String login){
        Optional<User> user = userService.findUser(login);
        if(user.isPresent()){
            Optional<Order> order = orderService.findOrder(orderId);
            return order.map(value -> ResponseEntity.ok(new GetOrderItemsResponse(orderItemService.findAllIds(value))))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }else{
            return ResponseEntity.notFound().build();
        }



    }


}
