package com.mesakh.firststartspringboot.controller.admin;

import com.mesakh.firststartspringboot.models.GroupUser;
import com.mesakh.firststartspringboot.repository.GroupRepository;
import com.mesakh.firststartspringboot.repository.GroupUserRepository;
import com.mesakh.firststartspringboot.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GroupController {
    private final GroupUserRepository groupUserRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupController(GroupUserRepository groupUserRepository, GroupRepository groupRepository, UserRepository userRepository) {
        this.groupUserRepository = groupUserRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/admin/groups")
    public String index(Model model){
        model.addAttribute("groups",groupRepository.findAll());
        return "admin/group/index";
    }

    @GetMapping("/admin/groups/view/{id}")
    public String getViewGroupById(Model model, @PathVariable ("id") int id){
        var groupUsers = groupUserRepository.findAllByGroup_Id(id);
        var group = groupRepository.findById(id).orElse(null);
        model.addAttribute("group_users",groupUsers);
        model.addAttribute("group",group);
        return "admin/group/view";
    }

    @GetMapping("/admin/groups/view/add-member/{id}")
    public String getViewGroupAddMember(Model model, @PathVariable ("id") int id){
//        var groupUsers = groupUserRepository.findAllByGroup_Id(id);
        var group = groupRepository.findById(id).orElse(null);
        var userGroup = new GroupUser();
        userGroup.setUserList(userRepository.findAll());
        model.addAttribute("user_group",userGroup);
//        model.addAttribute("group_users",groupUsers);
        model.addAttribute("group",group);
        return "admin/group/add_member";
    }

    @PostMapping("/admin/group/view/create/{id}")
    public String getViewGroupCreateMember(@ModelAttribute("user_group") GroupUser groupUser ,@PathVariable ("id") int id){
//        var groupUsers = groupUserRepository.findAllByGroup_Id(id);
//        var group = groupRepository.findById(id).orElse(null);
        var group = groupRepository.findById(id).orElse(null);
        var userGroup = new GroupUser();
        userGroup.setGroup(group);
        userGroup.setUser(groupUser.getUser());
        groupUserRepository.save(userGroup);
//        userGroup.setUserList(userRepository.findAll());
//        model.addAttribute("user_group",userGroup);
//        model.addAttribute("group_users",groupUsers);
//        model.addAttribute("group",group);
        return "redirect:/admin/groups/view/"+id;
    }

}
