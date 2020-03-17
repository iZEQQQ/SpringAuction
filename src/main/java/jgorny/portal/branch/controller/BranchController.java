package jgorny.portal.branch.controller;

import jgorny.portal.branch.controller.model.GetBranchResponse;
import jgorny.portal.branch.controller.model.GetBranchesResponse;
import jgorny.portal.branch.controller.model.PostBranchRequest;
import jgorny.portal.branch.controller.model.PutBranchRequest;
import jgorny.portal.branch.serviece.BranchService;
import jgorny.portal.branch.serviece.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private BranchService service;

    @Autowired
    public BranchController(BranchService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<GetBranchResponse> getBranch(@PathVariable("id") Long id) {
        Optional<Branch> branch = service.findBranch(id);
        return branch.map(value -> ResponseEntity.ok(new GetBranchResponse(value.getId(), value.getName())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public GetBranchesResponse getBranches() {
        return new GetBranchesResponse(service.findAllIds());
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
