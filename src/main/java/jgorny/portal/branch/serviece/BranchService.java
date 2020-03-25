package jgorny.portal.branch.serviece;

import jgorny.portal.branch.repository.BranchRepository;
import jgorny.portal.branch.serviece.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    private BranchRepository repository;

    @Autowired
    public BranchService(BranchRepository repository) {
        this.repository = repository;
    }

    public List<Branch> findAllBranches() {
        return repository.findAll();
    }

    public List<Long> findAllIds() {
        return repository.findId();
    }

    public Optional<Branch> findBranch(Long id) {
        return repository.findById(id);
    }
    public void createBranch(Branch branch){
        repository.save(branch);
    }

    public void updateBranch(Branch branch){
        repository.save(branch);
    }
    public void deleteBranch(Branch branch){
        repository.delete(branch);
    }
}
