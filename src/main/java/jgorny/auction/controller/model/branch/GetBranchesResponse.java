package jgorny.auction.controller.model.branch;

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
