package jgorny.auction.controller;

import jgorny.auction.controller.model.GetBranchResponse;
import jgorny.auction.controller.model.GetBranchesResponse;
import jgorny.auction.controller.model.PostBranchRequest;
import jgorny.auction.controller.model.PutBranchRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @GetMapping("{id}")
    public GetBranchResponse getBranch(@PathVariable("id") Long id) {
        return new GetBranchResponse(id, "meat");
    }

    @GetMapping("")
    public GetBranchesResponse getBranches() {
        return new GetBranchesResponse(List.of(1l, 3l, 9l, 5l, 6l, 8l, 4l, 7l));
    }

    @PostMapping("")
    public void postBranch(@RequestBody PostBranchRequest branch) {
        System.out.println(branch);
    }

    @PutMapping("{id}")
    public void putBranch(@PathVariable("id") Long id, @RequestBody PutBranchRequest branch) {
        System.out.println(id +" "+ branch);
    }

    @DeleteMapping("{id}")
    public void deleteBranch(@PathVariable("id") Long id){
        System.out.println(id);
    }

}
