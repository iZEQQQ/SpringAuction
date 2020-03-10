package jgorny.auction.controller;

import jgorny.auction.controller.model.category.GetCategoriesResponse;
import jgorny.auction.controller.model.category.GetCategoryResponse;
import jgorny.auction.controller.model.category.PostCategoryRequest;
import jgorny.auction.controller.model.category.PutCategoryRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches/{id}/categories")
public class CategoryController {

    @GetMapping("{id}")
    public GetCategoryResponse getCategory(@PathVariable("id") Long id){
        return new GetCategoryResponse(id, "phones");
    }

    @GetMapping("")
    public GetCategoriesResponse getCategories(@PathVariable("id") Long id){
        return new GetCategoriesResponse(List.of(24l,8l,2l,42l,4l,2l));
    }
    @PostMapping("")
    public void postCategory(@RequestBody PostCategoryRequest category){
        System.out.println(category);
    }

    @PutMapping("{id}")
    public void putBranch(@PathVariable("id") Long id, @RequestBody PutCategoryRequest category){
        System.out.println(id+" "+category);
    }


    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        System.out.println(id);
    }

}
