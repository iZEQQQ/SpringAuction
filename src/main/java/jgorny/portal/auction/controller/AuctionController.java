package jgorny.portal.auction.controller;

import jgorny.portal.auction.controller.model.GetAuctionResponse;
import jgorny.portal.auction.controller.model.GetAuctionsResponse;
import jgorny.portal.auction.controller.model.PostAuctionRequest;
import jgorny.portal.auction.controller.model.PutAuctionRequest;
import jgorny.portal.auction.serviece.AuctionServiece;
import jgorny.portal.auction.serviece.model.Auction;
import jgorny.portal.branch.serviece.BranchService;
import jgorny.portal.branch.serviece.model.Branch;
import jgorny.portal.category.controller.model.GetCategoryResponse;
import jgorny.portal.category.serviece.CategoryServiece;
import jgorny.portal.category.serviece.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branches/{branchId}/categories/{categoryId}/auctions")
public class AuctionController {

    private BranchService branchService;

    private CategoryServiece categoryServiece;

    private AuctionServiece auctionServiece;

    @Autowired
    public AuctionController(BranchService branchService, CategoryServiece categoryServiece, AuctionServiece auctionServiece) {
        this.branchService = branchService;
        this.categoryServiece = categoryServiece;
        this.auctionServiece = auctionServiece;
    }

    @GetMapping("{id}")
    public ResponseEntity<GetAuctionResponse> getAuction(@PathVariable("branchId") Long branchId, @PathVariable("categoryId") Long categoryId, @PathVariable("id") Long id) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        if (branch.isPresent()) {
            Optional<Category> category = categoryServiece.findCategory(categoryId);
            if (category.isPresent()) {
                Optional<Auction> auction = auctionServiece.findAuction(id);
                if (auction.isPresent()) {
                    return ResponseEntity.ok(new GetAuctionResponse(auction.get().getId(), auction.get().getName(), auction.get().getPrice()));
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping("")
    public ResponseEntity<GetAuctionsResponse> getAuctions(@PathVariable("branchId") Long branchId, @PathVariable("categoryId") Long categoryId) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        if (branch.isPresent()) {
            Optional<Category> category = categoryServiece.findCategory(categoryId);
            return category.map(value -> ResponseEntity.ok(new GetAuctionsResponse(auctionServiece.findAllIds(value))))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> postAuction(@PathVariable("branchId") Long branchId, @PathVariable("categoryId") Long categoryId, @RequestBody PostAuctionRequest auction) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        if (branch.isPresent()) {
            Optional<Category> category = categoryServiece.findCategory(categoryId);
            if (category.isPresent()) {
                Auction auction1 = new Auction(auction.getName(), auction.getPrice(), category.get());
                auctionServiece.createAuction(auction1);
                return ResponseEntity.created(URI.create("http://localhost:8080/api/branches/" + branchId + "/categories/" + categoryId + "/auctions/" + auction1.getId())).build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public void putAuction(@PathVariable("branchId") Long branchId, @PathVariable("categoryId") Long categoryId, @PathVariable("id") Long id, @RequestBody PutAuctionRequest auction) {

    }

    @DeleteMapping("{id}")
    public void deleteAuction(@PathVariable("branchId") Long branchId, @PathVariable("categoryId") Long categoryId, @PathVariable("id") Long id) {
        System.out.println(id);
    }


}
