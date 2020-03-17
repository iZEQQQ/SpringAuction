package jgorny.portal.branch.serviece;

import jgorny.portal.branch.repository.BranchRepository;
import jgorny.portal.branch.serviece.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    private BranchRepository repository;

    @Autowired
    public BranchService(BranchRepository repository) {
        this.repository = repository;
    }

    public List<Branch> findAllBranches(){
        return repository.findAll();
    }

}
