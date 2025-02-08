package com.mesakh.firststartspringboot.service.impl;

import com.mesakh.firststartspringboot.constants.Constants;
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
        if(0==request.getId()){
            user.setPassword(request.getPassword());
        }
//        user.setPassword(request.getPassword());
        user.setStatus(Constants.STATUS_ACTIVE);
        if(request.getRole() != null) {
            user.setRole(request.getRole());
        }else{
            user.setRole("Constants.ROLE_USER");
        }
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
            findUser.setStatus(Constants.STATUS_DELETE);
            userRepository.save(findUser);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<String> statusList = new ArrayList<>();
        statusList.add(Constants.STATUS_ACTIVE);
        statusList.add(Constants.STATUS_DELETE);
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
