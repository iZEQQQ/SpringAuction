package jgorny.portal.branch.controller.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetBranchesResponse {

    private List<Long> ids = new ArrayList<>();

}
