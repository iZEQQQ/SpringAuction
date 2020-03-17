package jgorny.portal.branch.repository;

import jgorny.portal.branch.serviece.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {

    @Query("select b.id from Branch b")
    List<Long> findId();

}
