package jgorny.auction.controller;

import jgorny.auction.controller.model.auction.GetAuctionResponse;
import jgorny.auction.controller.model.auction.GetAuctionsResponse;
import jgorny.auction.controller.model.auction.PutAuctionRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches/{id}/categories/{id}/auctions")
public class AuctionController {

    @GetMapping("{id}")
    public GetAuctionResponse getAuction(@PathVariable("id") Long id){
        return new GetAuctionResponse(id,"book");
    }

    @GetMapping("")
    public GetAuctionsResponse getAuctions(){
        return new GetAuctionsResponse(List.of(1,2,3,4,5,6,7));
    }

    @PutMapping("{id}")
    public void putAuction(@PathVariable("id") Long id, @RequestBody PutAuctionRequest auction){
        System.out.println(id+" "+auction);
    }

    @DeleteMapping("{id}")
    public void deleteAuction(@PathVariable("id") Long id){
        System.out.println(id);
    }


}
