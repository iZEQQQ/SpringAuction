package jgorny.portal.basket.controller.model;


import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BasketItem {

    private int basketElementId;

    private String auctionName;

    private int quantity;

    private Double price;

}
