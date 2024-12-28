package com.mesakh.firststartspringboot.service.impl;

import com.mesakh.firststartspringboot.models.User;
import com.mesakh.firststartspringboot.models.request.UserRequest;
import com.mesakh.firststartspringboot.repository.UserRepository;
import com.mesakh.firststartspringboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    // Dependency inject => Create new object
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getAllUserActive() {
        return userRepository.findAllByStatus("ACT");
    }

    @Override
    public void insertAndUpdate(UserRequest request) {
        User user = new User();
        user.setId(request.id);
        user.setUsername(request.getUsername());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setStatus("ACT");
        userRepository.save(user);
    }
}
