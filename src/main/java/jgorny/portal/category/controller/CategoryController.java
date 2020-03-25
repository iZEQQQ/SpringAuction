package jgorny.portal.category.controller;

import jgorny.portal.branch.serviece.BranchService;
import jgorny.portal.branch.serviece.model.Branch;
import jgorny.portal.category.controller.model.GetCategoriesResponse;
import jgorny.portal.category.controller.model.GetCategoryResponse;
import jgorny.portal.category.controller.model.PostCategoryRequest;
import jgorny.portal.category.controller.model.PutCategoryRequest;
import jgorny.portal.category.serviece.CategoryServiece;
import jgorny.portal.category.serviece.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branches/{branchId}/categories")
public class CategoryController {

    private BranchService branchService;


    private CategoryServiece categoryServiece;

    @Autowired
    public CategoryController(BranchService branchService, CategoryServiece categoryServiece) {
        this.branchService = branchService;
        this.categoryServiece = categoryServiece;
    }


    @GetMapping("{id}")
    public ResponseEntity<GetCategoryResponse> getCategory(@PathVariable("branchId") Long branchId, @PathVariable("id") Long id) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        if (branch.isPresent()) {
            Optional<Category> category = categoryServiece.findCategory(id);
            return category.map(value -> ResponseEntity.ok(new GetCategoryResponse(value.getId(), value.getName())))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<GetCategoriesResponse> getCategories(@PathVariable("branchId") Long branchId) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        return branch.map(value -> ResponseEntity.ok(new GetCategoriesResponse(categoryServiece.findAllIds(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Void> postCategory(@PathVariable("branchId") Long branchId, @RequestBody PostCategoryRequest category) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        if (branch.isPresent()) {
            Category category1 = new Category(category.getName(), branch.get());
            categoryServiece.createCategory(category1);
            return ResponseEntity.created(URI.create("http://localhost:8080/api/branches/" + branchId + "/categories/" + category1.getId())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public void putBranch(@PathVariable("branchId") Long branchId, @PathVariable("id") Long id, @RequestBody PutCategoryRequest category) {
        System.out.println(id + " " + category);
    }


    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable("branchId") Long branchId, @PathVariable("id") Long id) {
        System.out.println(id);
    }

}
