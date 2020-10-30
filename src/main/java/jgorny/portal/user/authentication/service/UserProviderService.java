package jgorny.portal.user.authentication.service;

import jgorny.portal.user.authentication.model.UserDetailsAdapter;
import jgorny.portal.user.repository.model.User;
import jgorny.portal.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserProviderService implements UserDetailsService {

    private final UserService service;

    @Autowired
    public UserProviderService(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = service.findUser(login);
        if(user.isEmpty()){
            throw new UsernameNotFoundException(login);
        }
        return new UserDetailsAdapter(user.get());
    }




}
