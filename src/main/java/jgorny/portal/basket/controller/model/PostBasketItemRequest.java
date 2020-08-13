package jgorny.portal.basket.controller.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostBasketItemRequest {

    private Long auctionId;

    private int quantity;

}
