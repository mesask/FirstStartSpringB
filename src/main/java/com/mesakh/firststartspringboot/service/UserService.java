package com.mesakh.firststartspringboot.service;

import com.mesakh.firststartspringboot.models.User;
import com.mesakh.firststartspringboot.models.request.UserRequest;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    List<User> getAllUserActive();
    void insertAndUpdate(UserRequest request);
    User getUserById(int id);
    void deleteUser(Integer id);
    List<User> getAllUsers();
}
