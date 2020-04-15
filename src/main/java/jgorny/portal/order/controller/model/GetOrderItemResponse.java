package jgorny.portal.order.controller.model;

import jgorny.portal.auction.controller.model.GetAuctionResponse;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderItemResponse {

    private Long id;

    private int quantity;

    private GetAuctionResponse auction;

}
