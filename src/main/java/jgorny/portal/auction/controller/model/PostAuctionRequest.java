package jgorny.portal.auction.controller.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostAuctionRequest {

    private String name;

    private Double price;

}
