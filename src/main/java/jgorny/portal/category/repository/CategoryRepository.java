package jgorny.portal.category.repository;

import jgorny.portal.branch.repository.model.Branch;
import jgorny.portal.category.repository.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("select c.id from Category c")
    List<Long> findId();

    @Query("select c.id from Category c where c.branch = :branch")
    List<Long> findId(Branch branch);

}
