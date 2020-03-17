package jgorny.portal.branch.controller;

import jgorny.portal.branch.controller.model.GetBranchResponse;
import jgorny.portal.branch.controller.model.GetBranchesResponse;
import jgorny.portal.branch.controller.model.PostBranchRequest;
import jgorny.portal.branch.controller.model.PutBranchRequest;
import jgorny.portal.branch.serviece.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private BranchService service;

    @Autowired
    public BranchController(BranchService service) {
        this.service = service;
    }

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
