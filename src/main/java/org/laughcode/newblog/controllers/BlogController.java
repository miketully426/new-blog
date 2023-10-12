package org.laughcode.newblog.controllers;

import org.laughcode.newblog.data.BlogData;
import org.laughcode.newblog.models.Blog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BlogController {

    private BlogData blogData = new BlogData();

    @GetMapping
    public String home(Model model){
        model.addAttribute("title", "Mike's Blog");
        model.addAttribute("blogs", BlogData.getAll());
        return "home";
    }

    @GetMapping("/new-post")
    public String newPostForm(){
        return "newPost";
    }

    @PostMapping("/new-post")
    public String handlePostForm(Model model, @ModelAttribute Blog blog){
        model.addAttribute("blog", blog);
        blogData.add(blog);
        return "displayPost";
    }

}
