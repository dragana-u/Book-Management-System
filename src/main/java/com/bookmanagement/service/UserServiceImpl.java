package com.bookmanagement.service;

import com.bookmanagement.model.Role;
import com.bookmanagement.model.User;
import com.bookmanagement.model.exceptions.UsernameAndPasNotFoundException;
import com.bookmanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String login, String password, String email, Role role) {
        if (login == "" || password == ""){
            return null;
        }
        User user = new User(login,passwordEncoder.encode(password),email,role);
        return userRepository.save(user);
    }

    public User authentication(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password).orElseThrow(UsernameAndPasNotFoundException::new);
    }
    public UserDetails findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(UsernameAndPasNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(UsernameAndPasNotFoundException::new);
    }
}
