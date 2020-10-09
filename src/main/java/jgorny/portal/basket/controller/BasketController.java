package jgorny.portal.basket.controller;


import jgorny.portal.auction.repository.model.Auction;
import jgorny.portal.auction.service.AuctionService;
import jgorny.portal.basket.bean.BasketBean;
import jgorny.portal.basket.controller.model.BasketItem;
import jgorny.portal.basket.controller.model.GetBasketItemsResponse;
import jgorny.portal.basket.controller.model.PostBasketItemRequest;
import jgorny.portal.basket.controller.model.PutBasketItemRequest;
import jgorny.portal.user.repository.model.User;
import jgorny.portal.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@CrossOrigin(allowCredentials = "true")
@RestController
@Scope("request")
@RequestMapping("/api/users/{login}/basket/items")
public class BasketController {

    private BasketBean bean;

    private AuctionService auctionService;

    private UserService userService;

    @Autowired
    public BasketController(BasketBean bean, AuctionService auctionService, UserService userService) {
        this.bean = bean;
        this.auctionService = auctionService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<GetBasketItemsResponse> getItems(@PathVariable("login") String login) {
        Optional<User> user = userService.findUser(login);
        if (user.isPresent()) {
            AtomicInteger index = new AtomicInteger(0);
            return ResponseEntity.ok(new GetBasketItemsResponse(bean.findAll().stream()
                    .map(basketItem -> new BasketItem(index.incrementAndGet(),
                            basketItem.getAuction().getName(),
                            basketItem.getQuantity(),
                            basketItem.getAuction().getPrice()))
                    .collect(Collectors.toList())));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> postItem(@PathVariable("login") String login, @RequestBody PostBasketItemRequest request) {
        Optional<User> user = userService.findUser(login);
        if (user.isPresent()) {
            Optional<Auction> auction = auctionService.findAuction(request.getAuctionId());
            if (auction.isPresent()) {
                jgorny.portal.basket.bean.model.BasketItem item = new jgorny.portal.basket.bean.model.BasketItem(
                        request.getQuantity(), auction.get());
                bean.save(item);
                return ResponseEntity.created(URI.create("http://localhost:8080/api/users/" + login + "/basket/items/" + bean.findAll().size())).build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{itemId}")
    public ResponseEntity<Void> putItem(@PathVariable("login") String login, @PathVariable("itemId") int itemId, @RequestBody PutBasketItemRequest request) {
        Optional<User> user = userService.findUser(login);
        if (user.isPresent()) {
            if (bean.findAll().size() >= itemId && itemId > 0) {
                jgorny.portal.basket.bean.model.BasketItem original = bean.findAll().get(itemId - 1);
                jgorny.portal.basket.bean.model.BasketItem modified = new jgorny.portal.basket.bean.model.BasketItem(request.getQuantity(), original.getAuction());
                bean.save(modified);
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("login") String login, @PathVariable("itemId") int itemId) {
        Optional<User> user = userService.findUser(login);
        if (user.isPresent()) {
            if (bean.findAll().size() >= itemId && itemId > 0) {
                bean.deleteById(itemId - 1);
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
