package jgorny.portal.branch.repository;

import jgorny.portal.branch.serviece.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {



}
