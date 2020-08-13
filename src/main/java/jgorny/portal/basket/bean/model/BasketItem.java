package jgorny.portal.basket.bean.model;

import jgorny.portal.auction.repository.model.Auction;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BasketItem {

    private int quantity;

    private Auction auction;

}
