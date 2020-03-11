package jgorny.auction.controller.model.auction;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetAuctionsResponse {

    private List<Long> ids = new ArrayList<>();

}
