package com.mesakh.firststartspringboot.service;

import com.mesakh.firststartspringboot.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var checkUsername = userRepository.findByUsernameAndStatus(username,"ACT");
        if(checkUsername.isEmpty()) {
            checkUsername = userRepository.findByEmailAndStatus(username,"ACT");
            if(checkUsername.isEmpty()) {
                checkUsername = userRepository.findByPhoneNumberAndStatus(username,"ACT");

            }
        }
        if(checkUsername.isEmpty()) {
            throw new UsernameNotFoundException("Your Username & Password not correct");
        }
        return User.withUsername(checkUsername.get().getUsername()).password(checkUsername.get().getPassword()).roles(checkUsername.get().getRole()).build();
    }
}
