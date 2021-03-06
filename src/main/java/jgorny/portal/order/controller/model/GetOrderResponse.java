package jgorny.portal.order.controller.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderResponse {

    private Long id;

    private LocalDateTime localDateTime;

}
