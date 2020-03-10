package jgorny.auction.controller.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryResponse {

    private String name;

    private Long id;

}
