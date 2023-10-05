package org.laughcode.newblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BlogController {

    private Map<String, String> statusStore = new HashMap<>();

    @GetMapping
    public String home(Model model){
        statusStore.put("Test", "Sad");
        model.addAttribute("title", "Mike's Blog");
        model.addAttribute("statuses", statusStore);
        return "home";
    }

    @GetMapping("/new-post")
    public String newPostForm(){
        return "newPost";
    }

    @PostMapping("/new-post")
    public String handlePostForm(Model model, @RequestParam String name, @RequestParam String status){
        model.addAttribute("name", name);
        model.addAttribute("status", status);
        statusStore.put(name, status);
        return "displayPost";
    }

}
