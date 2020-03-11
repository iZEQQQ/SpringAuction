package jgorny.auction.controller.model.auction;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetAuctionResponse {

    private Long id;

    private String name;

    private Long price;

}
