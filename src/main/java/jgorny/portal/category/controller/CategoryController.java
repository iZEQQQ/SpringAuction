package jgorny.portal.category.controller;

import jgorny.portal.category.controller.model.GetCategoriesResponse;
import jgorny.portal.category.controller.model.GetCategoryResponse;
import jgorny.portal.category.controller.model.PostCategoryRequest;
import jgorny.portal.category.controller.model.PutCategoryRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches/{branchId}/categories")
public class CategoryController {

    @GetMapping("{id}")
    public GetCategoryResponse getCategory(@PathVariable("branchId")int branchId, @PathVariable("id") Long id){
        return new GetCategoryResponse(id, "phones");
    }

    @GetMapping("")
    public GetCategoriesResponse getCategories(@PathVariable("branchId")int branchId){
        return new GetCategoriesResponse(List.of(24l,8l,2l,42l,4l,2l));
    }
    @PostMapping("")
    public void postCategory(@PathVariable("branchId")int branchId,@RequestBody PostCategoryRequest category){
        System.out.println(category);
    }

    @PutMapping("{id}")
    public void putBranch(@PathVariable("branchId")int branchId,@PathVariable("id") Long id, @RequestBody PutCategoryRequest category){
        System.out.println(id+" "+category);
    }


    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable("branchId")int branchId,@PathVariable("id") Long id){
        System.out.println(id);
    }

}
