package jgorny.portal.branch.controller;

import jgorny.portal.branch.controller.model.GetBranchResponse;
import jgorny.portal.branch.controller.model.GetBranchesResponse;
import jgorny.portal.branch.controller.model.PostBranchRequest;
import jgorny.portal.branch.controller.model.PutBranchRequest;
import jgorny.portal.branch.service.BranchService;
import jgorny.portal.branch.repository.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private BranchService service;

    @Autowired
    public BranchController(BranchService service) {
        this.service = service;
    }

    @GetMapping("{branchId}")
    public ResponseEntity<GetBranchResponse> getBranch(@PathVariable("branchId") Long branchId) {
        Optional<Branch> branch = service.findBranch(branchId);
        return branch.map(value -> ResponseEntity.ok(new GetBranchResponse(value.getId(), value.getName())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public GetBranchesResponse getBranches() {
        return new GetBranchesResponse(service.findAllIds());
    }

    @PostMapping("")
    public ResponseEntity<Void> postBranch(@RequestBody PostBranchRequest request) {
        Branch branch = new Branch(request.getName());
        service.createBranch(branch);
        return ResponseEntity.created(URI.create("http://localhost:8080/api/branches/" + branch.getId())).build();
    }

    @PutMapping("{branchId}")
    public ResponseEntity<Void> putBranch(@PathVariable("branchId") Long branchId, @RequestBody PutBranchRequest request) {
        Optional<Branch> branch = service.findBranch(branchId);
        if (branch.isPresent()) {
            branch.get().setName(request.getName());
            service.updateBranch(branch.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{branchId}")
    public ResponseEntity<Void> deleteBranch(@PathVariable("branchId") Long branchId) {
        Optional<Branch> branch = service.findBranch(branchId);
        if (branch.isPresent()) {
            service.deleteBranch(branch.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


