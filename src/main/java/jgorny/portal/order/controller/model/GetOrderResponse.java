package jgorny.portal.order.controller.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderResponse {

    private Long id;

    private Date date;

}
