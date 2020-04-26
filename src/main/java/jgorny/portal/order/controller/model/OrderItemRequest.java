package jgorny.portal.order.controller.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {

    private Long auctionId;

    private int quantity;

}
