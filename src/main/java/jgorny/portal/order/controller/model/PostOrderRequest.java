package jgorny.portal.order.controller.model;

import lombok.*;

import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostOrderRequest {

    private List<OrderItemRequest> items;


}
