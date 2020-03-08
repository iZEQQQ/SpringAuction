package jgorny.auction.controller;

import jgorny.auction.controller.model.GetBranchResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @GetMapping("{id}")
    public GetBranchResponse getBranch(@PathVariable("id") Long id){
        return new GetBranchResponse(id,"meat");
    }



}
