package jgorny.auction.controller.model.category;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoriesResponse {

    private List<Long> ids = new ArrayList<>();

}