package jgorny.portal.category.controller;

import jgorny.portal.branch.service.BranchService;
import jgorny.portal.branch.repository.model.Branch;
import jgorny.portal.category.controller.model.GetCategoriesResponse;
import jgorny.portal.category.controller.model.GetCategoryResponse;
import jgorny.portal.category.controller.model.PostCategoryRequest;
import jgorny.portal.category.controller.model.PutCategoryRequest;
import jgorny.portal.category.service.CategoryService;
import jgorny.portal.category.repository.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/api/branches/{branchId}/categories")
public class CategoryController {

    private BranchService branchService;


    private CategoryService categoryService;

    @Autowired
    public CategoryController(BranchService branchService, CategoryService categoryService) {
        this.branchService = branchService;
        this.categoryService = categoryService;
    }


    @GetMapping("{categoryId}")
    public ResponseEntity<GetCategoryResponse> getCategory(@PathVariable("branchId") Long branchId, @PathVariable("categoryId") Long categoryId) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        if (branch.isPresent()) {
            Optional<Category> category = categoryService.findCategory(categoryId);
            return category.map(value -> ResponseEntity.ok(new GetCategoryResponse(value.getId(), value.getName())))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<GetCategoriesResponse> getCategories(@PathVariable("branchId") Long branchId) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        return branch.map(value -> ResponseEntity.ok(new GetCategoriesResponse(categoryService.findAllIds(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Void> postCategory(@PathVariable("branchId") Long branchId, @RequestBody PostCategoryRequest request) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        if (branch.isPresent()) {
            Category category = new Category(request.getName(), branch.get());
            categoryService.createCategory(category);
            return ResponseEntity.created(URI.create("http://localhost:8080/api/branches/" + branchId + "/categories/" + category.getId())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<Void> putCategory(@PathVariable("branchId") Long branchId, @PathVariable("categoryId") Long categoryId, @RequestBody PutCategoryRequest request) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        if (branch.isPresent()) {
            Optional<Category> category = categoryService.findCategory(categoryId);
            if (category.isPresent()) {
                category.get().setName(request.getName());
                categoryService.updateCategory(category.get());
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("branchId") Long branchId, @PathVariable("categoryId") Long categoryId) {
        Optional<Branch> branch = branchService.findBranch(branchId);
        if (branch.isPresent()) {
            Optional<Category> category = categoryService.findCategory(categoryId);
            if (category.isPresent()) {
                categoryService.deleteCategory(category.get());
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.notFound().build();
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
