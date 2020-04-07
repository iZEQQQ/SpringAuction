package jgorny.portal.user.serviece;

import jgorny.portal.branch.repository.model.Branch;
import jgorny.portal.user.repository.UserRepository;
import jgorny.portal.user.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public List<String> findAllLogins() {
        return repository.findLogin();
    }

    public Optional<User> findUser(String login) {
        return repository.findById(login);
    }

    public void createUser(User user) {
        repository.save(user);
    }

    public void updateUser(User user) {
        repository.save(user);
    }

    public void deleteUser(User user) {
        repository.delete(user);
    }


}
