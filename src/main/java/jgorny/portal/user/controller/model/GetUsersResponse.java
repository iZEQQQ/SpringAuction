package jgorny.portal.user.controller.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponse {

    private List<Long> ids = new ArrayList<>();

}
