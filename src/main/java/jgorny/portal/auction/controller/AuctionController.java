package jgorny.portal.auction.controller;

import jgorny.portal.auction.controller.model.GetAuctionResponse;
import jgorny.portal.auction.controller.model.GetAuctionsResponse;
import jgorny.portal.auction.controller.model.PutAuctionRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches/{branchId}/categories/{categoryId}/auctions")
public class AuctionController {

    @GetMapping("{id}")
    public GetAuctionResponse getAuction(@PathVariable("branchId") int branchId, @PathVariable("categoryId") int categoryId, @PathVariable("id") Long id) {
        return new GetAuctionResponse(id, "book", 20d);
    }

    @GetMapping("")
    public GetAuctionsResponse getAuctions(@PathVariable("branchId") int branchId, @PathVariable("categoryId") int categoryId) {
        return new GetAuctionsResponse(List.of(1l, 2l, 3l, 4l, 5l, 6l, 7l));
    }

    @PutMapping("{id}")
    public void putAuction(@PathVariable("branchId") int branchId, @PathVariable("categoryId") int categoryId, @PathVariable("id") Long id, @RequestBody PutAuctionRequest auction) {
        System.out.println(id + " " + auction);
    }

    @DeleteMapping("{id}")
    public void deleteAuction(@PathVariable("branchId") int branchId, @PathVariable("categoryId") int categoryId, @PathVariable("id") Long id) {
        System.out.println(id);
    }


}
