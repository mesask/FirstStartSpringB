package com.mesakh.firststartspringboot.controller;

import com.mesakh.firststartspringboot.models.User;
import com.mesakh.firststartspringboot.models.request.UserRequest;
import com.mesakh.firststartspringboot.models.response.KeyValueItem;
import com.mesakh.firststartspringboot.repository.PositionRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import com.mesakh.firststartspringboot.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final PositionRepository positionRepository;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, PositionRepository positionRepository){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.positionRepository = positionRepository;
//        this.passwordEncoder = new passwordEncoder;
    }

    @GetMapping("/users")
    public String index(Model model){
        List<User> listUse = userService.getAllUsers();
        model.addAttribute("users", listUse);
        return "admin/user/index";
    }

    @GetMapping("/users/add")
    public String add(Model model){
        var user = new UserRequest();
        user.setRoleList(KeyValueItem.getAllRoles());
        user.setStatusList(KeyValueItem.getAllStatus());
        user.setPositionList(positionRepository.findAll());
        model.addAttribute("user", user);
        return "admin/user/form";
    }

    @PostMapping("/users/create")
    public String create(@ModelAttribute("user") UserRequest userRequest){
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
//        model.addAttribute("user", new UserRequest());
        userService.insertAndUpdate(userRequest);
        return "redirect:/admin/users";
    }
//@PostMapping("/users/create")
//public String create(@ModelAttribute("user") UserRequest userRequest)
//{
//    try {
//        // Encode the password before saving
//        if (user.getPassword() != null && !userRequest.getPassword().isEmpty()) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        }
//
//        // Save the new user
//        userService.insertAndUpdate(userRequest);
//        return "redirect:/admin/users";
//    } catch (Exception e) {
//        // Handle any errors
//        return "redirect:/admin/users";
//    }
//}


    @GetMapping("/users/edit/{id}")
    public String edit(@PathVariable("id") Integer Id, Model model){
        UserRequest userRequest = new UserRequest();
        var user = userService.getUserById(Id);
        userRequest.setId(user.getId());
        userRequest.setUsername(user.getUsername());
        userRequest.setEmail(user.getEmail());
//        userRequest.setPassword(user.getPassword());
        userRequest.setPassword("");
        userRequest.setPhoneNumber(user.getPhoneNumber());
        userRequest.setRoleList(KeyValueItem.getAllRoles());
        userRequest.setStatusList(KeyValueItem.getAllStatus());
        userRequest.setPositionList(positionRepository.findAll());
        userRequest.setPositionId(user.getPosition().getId());
        model.addAttribute("user", userRequest);
        return "admin/user/form";
    }
//@GetMapping("/users/edit/{id}")
//public String edit (@PathVariable("id") Integer id,
//                    Model model,
//                    RedirectAttributes redirectAttributes)
//{
//    User userRequest = new User();
////        User user = new User();
//    var user = userService.getUserById(id);
//    userRequest.setId(user.getId());
//    userRequest.setUsername(user.getUsername());
//    userRequest.setPassword("");
//    userRequest.setEmail(user.getEmail());
//    userRequest.setPhoneNumber(user.getPhoneNumber());
//    model.addAttribute("user", userRequest);
//    return "admin/user/form";
//}


    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
//        UserRequest userRequest = new UserRequest();
//        var user = userService.getUserById(id);
//        userRequest.setId(user.getId());
//        userRequest.setUsername(user.getUsername());
//        userRequest.setEmail(user.getEmail());
//        userRequest.setPassword(user.getPassword());
//        userRequest.setPhoneNumber(user.getPhoneNumber());
//        model.addAttribute("user", userRequest);
//        return "admin/user/form";
    }


}
