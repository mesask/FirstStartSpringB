package com.mesakh.firststartspringboot.service.impl;

import com.mesakh.firststartspringboot.constants.Constants;
import com.mesakh.firststartspringboot.models.User;
import com.mesakh.firststartspringboot.models.request.UserRequest;
import com.mesakh.firststartspringboot.repository.PositionRepository;
import com.mesakh.firststartspringboot.repository.UserRepository;
import com.mesakh.firststartspringboot.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    // Dependency inject => Create new object
    private final UserRepository userRepository;
    private final PositionRepository positionRepository;
//    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PositionRepository positionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.positionRepository = positionRepository;
//        this.passwordEncoder = passwordEncoder;
//        private final PasswordEncoder passwordEncoder;
    }
    @Override
    public List<User> getAllUserActive() {
        //return userRepository.findAllByStatusInOrderByIdDesc("ACT","DEL");
        return userRepository.findAllByStatusOrderByIdDesc("ACT");
    }

    @Override
    public void insertAndUpdate(UserRequest request) {
//        User user = new User();
//        user.setId(request.getId());
//        user.setUsername(request.getUsername());
//        user.setPhoneNumber(request.getPhoneNumber());
//        user.setEmail(request.getEmail());
//        if(0==request.getId()) {
//            user.setPassword(request.getPassword());
//        }
//        user.setStatus(Constants.STATUS_ACTIVE);
//        user.setRole(user.getRole());
//        user.setPosition(positionRepository.findById(request.getPositionId()).orElse(null));
//        userRepository.save(user);
        User user = new User();
//        user.setPassword(request.getPassword());
        if(request.getId() != 0){
            user = userRepository.findById(request.getId()).orElse(null);
        }
        user.setId(request.id);
        user.setUsername(request.getUsername());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
//        if(0==request.getId()){
////            user.setPassword(request.getPassword());
//        }
        user.setPassword(request.getPassword());
        user.setStatus(Constants.STATUS_ACTIVE);
        user.setRole(request.getRole());
//        if(request.getRole() != null) {
//            user.setRole(request.getRole());
//        }else{
//            user.setRole("Constants.ROLE_USER");
//        }
        user.setPosition(positionRepository.findById(request.getPositionId()).orElse(null));
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
