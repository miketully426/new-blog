package org.laughcode.newblog.controllers;

import org.laughcode.newblog.data.UserRepository;
import org.laughcode.newblog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String addNewUser(Model model){
        model.addAttribute(new User());
        return "user/new";
    }

    @PostMapping
    public String handleNewUser(Model model, @ModelAttribute User user){
        model.addAttribute("user", user);
        userRepository.save(user);
        return "user/added";
    }
}
