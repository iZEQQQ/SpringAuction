package jgorny.portal.category.controller.model;

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