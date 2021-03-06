package jgorny.portal.auction.controller.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PutAuctionRequest {

    private String name;

    private Double price;

    public Integer quantity;

}
