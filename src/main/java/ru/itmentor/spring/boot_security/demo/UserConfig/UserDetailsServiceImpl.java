
package ru.itmentor.spring.boot_security.demo.UserConfig;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));

         if(user.isEmpty()){
             throw new UsernameNotFoundException("User not found");
         }
         return new UserDetails(user.get());
    }
}

