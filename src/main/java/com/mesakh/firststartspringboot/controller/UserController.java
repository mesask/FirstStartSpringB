package com.mesakh.firststartspringboot.controller;

import com.mesakh.firststartspringboot.models.User;
import com.mesakh.firststartspringboot.models.request.UserRequest;
import org.springframework.stereotype.Controller;
import com.mesakh.firststartspringboot.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public String index(Model model){
        List<User> listUse = userService.getAllUsers();
        model.addAttribute("users", listUse);
        return "admin/user/index";
    }

    @GetMapping("/users/add")
    public String add(Model model){
        model.addAttribute("user", new UserRequest());
        return "admin/user/form";
    }

    @PostMapping("/users/create")
    public String create(@ModelAttribute("user") UserRequest userRequest){
//        model.addAttribute("user", new UserRequest());
        userService.insertAndUpdate(userRequest);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{id}")
    public String edit(@PathVariable("id") Integer Id, Model model){
        UserRequest userRequest = new UserRequest();
        var user = userService.getUserById(Id);
        userRequest.setId(user.getId());
        userRequest.setUsername(user.getUsername());
        userRequest.setEmail(user.getEmail());
        userRequest.setPassword(user.getPassword());
        userRequest.setPhoneNumber(user.getPhoneNumber());
        model.addAttribute("user", userRequest);
        return "admin/user/form";
    }

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
