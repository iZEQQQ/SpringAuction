package jgorny.portal.basket.controller.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetBasketItemsResponse {

    private List<BasketItem> items = new ArrayList<>();

}
