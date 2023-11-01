package org.laughcode.newblog.controllers;

import jakarta.validation.Valid;
import org.laughcode.newblog.models.Blog;
import org.laughcode.newblog.models.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String newPostForm(Model model){
        model.addAttribute(new Blog());
        model.addAttribute("statuses", Status.values());
        return "newPost";
    }

    @PostMapping("/new-post")
    public String handlePostForm(Model model, @ModelAttribute @Valid Blog blog, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("blog", blog);
            model.addAttribute("statuses", Status.values());
            model.addAttribute("error message", "ERRORS ARE HAPPENING!!!!!");
            return "newPost";
        }
        blogData.add(blog);
        return "displayPost";
    }

}
