package com.mesakh.firststartspringboot.service.impl;

import com.mesakh.firststartspringboot.models.User;
import com.mesakh.firststartspringboot.models.request.UserRequest;
import com.mesakh.firststartspringboot.repository.UserRepository;
import com.mesakh.firststartspringboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        //return userRepository.findAllByStatusInOrderByIdDesc("ACT","DEL");
        return userRepository.findAllByStatusOrderByIdDesc("ACT");
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

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Integer id) {
        var findUser = userRepository.findById(id).orElse(null);
        if (findUser != null) {
            findUser.setStatus("DEL");
            userRepository.save(findUser);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<String> statusList = new ArrayList<>();
        statusList.add("ACT");
        statusList.add("DEL");
        return userRepository.findAllByStatusInOrderByIdDesc(statusList);
    }

    //    @Override
//    public User getUserById(int id) {
//        return userRepository.findById(id).orElse(null);
//    }

//    @Override
//    public void deleteUser(Integer id){
//        var findUser = userRepository.findById(id).orElse(null);
//        if (findUser != null) {
//            findUser.setStatus("DEL");
//            userRepository.save(findUser);
//        }
//    }
}
