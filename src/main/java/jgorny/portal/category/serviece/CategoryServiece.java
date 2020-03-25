package jgorny.portal.category.serviece;

import jgorny.portal.branch.serviece.model.Branch;
import jgorny.portal.category.repository.CategoryRepository;
import jgorny.portal.category.serviece.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiece {

    private CategoryRepository repository;

    @Autowired
    public CategoryServiece(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Long> findAllIds(Branch branch){
        return repository.findId(branch);
    }

    public Optional<Category> findCategory(Long id) {
        return repository.findById(id);
    }

    public void createCategory(Category category) {
        repository.save(category);
    }

    public void updateCategory(Category category) {
        repository.save(category);
    }

    public void deleteCategory(Category category) {
        repository.delete(category);
    }
}
