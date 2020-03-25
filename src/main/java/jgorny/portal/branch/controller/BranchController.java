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

import java.net.URI;
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
    public ResponseEntity<Void> postBranch(@RequestBody PostBranchRequest branch) {
        Branch b = new Branch(branch.getName());
        service.createBranch(b);
        return ResponseEntity.created(URI.create("http://localhost:8080/api/branches/" + b.getId())).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putBranch(@PathVariable("id") Long id, @RequestBody PutBranchRequest branch) {
        Optional<Branch> b = service.findBranch(id);
        if (b.isPresent()) {
            b.get().setName(branch.getName());
            service.updateBranch(b.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable("id") Long id) {
        Optional<Branch> b = service.findBranch(id);
        if (b.isPresent()) {
            service.deleteBranch(b.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


