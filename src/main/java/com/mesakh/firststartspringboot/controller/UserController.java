package com.mesakh.firststartspringboot.controller;

import com.mesakh.firststartspringboot.models.User;
import com.mesakh.firststartspringboot.models.request.UserRequest;
import org.springframework.stereotype.Controller;
import com.mesakh.firststartspringboot.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<User> listUse = userService.getAllUserActive();
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

}
