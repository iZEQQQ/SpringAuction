package jgorny.portal.order.controller.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersResponse {

    private List<Long> ids = new ArrayList<>();

}
