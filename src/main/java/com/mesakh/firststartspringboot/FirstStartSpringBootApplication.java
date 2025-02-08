package com.mesakh.firststartspringboot;

import com.mesakh.firststartspringboot.constants.Constants;
import com.mesakh.firststartspringboot.models.User;
import com.mesakh.firststartspringboot.models.request.UserRequest;
import com.mesakh.firststartspringboot.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FirstStartSpringBootApplication {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    public static void main(String[] args) {

        SpringApplication.run(FirstStartSpringBootApplication.class, args);
    }

    @PostConstruct
    public void dummyData() {
        if(userService.getAllUsers().isEmpty()){
            var userAdmin = new UserRequest();
            userAdmin.setUsername("admin");
            userAdmin.setPassword(passwordEncoder.encode("1234"));
            userAdmin.setEmail("admin@gmail.com");
            userAdmin.setPhoneNumber("010203040");
//            userAdmin.setStatus(Constants.STATUS_ACTIVE);
//            userAdmin.setRole(Constants.ROLE_ADMIN);
            userAdmin.setRole("Constants.ROLE_ADMIN");
            userService.insertAndUpdate(userAdmin);
        }

    }

}
