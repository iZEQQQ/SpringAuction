package jgorny.portal.auction.controller;

import jgorny.portal.auction.controller.model.GetAuctionResponse;
import jgorny.portal.auction.controller.model.GetAuctionsResponse;
import jgorny.portal.auction.controller.model.PostAuctionRequest;
import jgorny.portal.auction.controller.model.PutAuctionRequest;
import jgorny.portal.auction.service.AuctionService;
import jgorny.portal.auction.repository.model.Auction;
import jgorny.portal.branch.service.BranchService;
import jgorny.portal.branch.repository.model.Branch;
import jgorny.portal.category.service.CategoryService;
import jgorny.portal.category.repository.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/api/branches/{branchId}/categories/{categoryId}/auctions")
public class AuctionController {

    private BranchService branchService;

    private CategoryService categoryService;

    private AuctionService auctionService;

    @Autowired
    public AuctionController(BranchService branchService, CategoryService categoryService, AuctionService auctionService) {
        this.branchService = branchService;
        this.categoryService = categoryService;
        this.auctionService = auctionService;
    }

    @GetMapping("{auctionId}")
    public ResponseEntity<GetAuctionResponse> getAuction(@PathVariable("branchId") Long branchId, @PathVariable("categoryId") Long categoryId, @PathVariable("auctionId") Long auctionId) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        if (branch.isPresent()) {
            Optional<Category> category = categoryService.findCategory(categoryId);
            if (category.isPresent()) {
                Optional<Auction> auction = auctionService.findAuction(auctionId);
                return auction.map(value -> ResponseEntity.ok(new GetAuctionResponse(value.getId(), value.getName(), value.getPrice(), value.getQuantity())))
                        .orElseGet(() -> ResponseEntity.notFound().build());
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
            Optional<Category> category = categoryService.findCategory(categoryId);
            return category.map(value -> ResponseEntity.ok(new GetAuctionsResponse(auctionService.findAllIds(value))))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> postAuction(@PathVariable("branchId") Long branchId, @PathVariable("categoryId") Long categoryId, @RequestBody PostAuctionRequest request) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        if (branch.isPresent()) {
            Optional<Category> category = categoryService.findCategory(categoryId);
            if (category.isPresent()) {
                Auction auction = new Auction(request.getName(), request.getPrice(),request.getQuantity(), category.get());
                auctionService.createAuction(auction);
                return ResponseEntity.created(URI.create("http://localhost:8080/api/branches/" + branchId + "/categories/" + categoryId + "/auctions/" + auction.getId())).build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{auctionId}")
    public ResponseEntity<Void> putAuction(@PathVariable("branchId") Long branchId, @PathVariable("categoryId") Long categoryId, @PathVariable("auctionId") Long auctionId, @RequestBody PutAuctionRequest request) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        if (branch.isPresent()) {
            Optional<Category> category = categoryService.findCategory(categoryId);
            if (category.isPresent()) {
                Optional<Auction> auction = auctionService.findAuction(auctionId);
                if (auction.isPresent()) {
                    auction.get().setName(request.getName());
                    auction.get().setPrice(request.getPrice());
                    auction.get().setQuantity(request.getQuantity());
                    auctionService.updateAuction(auction.get());
                    return ResponseEntity.noContent().build();
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

    @DeleteMapping("{auctionId}")
    public ResponseEntity<Void> deleteAuction(@PathVariable("branchId") Long branchId, @PathVariable("categoryId") Long categoryId, @PathVariable("auctionId") Long auctionId) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        if (branch.isPresent()) {
            Optional<Category> category = categoryService.findCategory(categoryId);
            if (category.isPresent()) {
                Optional<Auction> auction = auctionService.findAuction(auctionId);
                if (auction.isPresent()) {
                    auctionService.deleteAuction(auction.get());
                    return ResponseEntity.accepted().build();
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

}
