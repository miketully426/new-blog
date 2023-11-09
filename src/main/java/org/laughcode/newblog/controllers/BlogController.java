package org.laughcode.newblog.controllers;

import jakarta.validation.Valid;
import org.laughcode.newblog.data.BlogRepository;
import org.laughcode.newblog.models.Blog;
import org.laughcode.newblog.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @GetMapping
    public String home(Model model){
        model.addAttribute("title", "Mike's Blog");
        model.addAttribute("blogs", blogRepository.findAll());
        return "home";
    }

    @GetMapping("blog/{id}")
    public String getSinglePost(Model model, @PathVariable String id){
        Optional<Blog> blog = blogRepository.findById(Integer.parseInt(id));
        model.addAttribute("blog", blog.get());
        return "displayPost";
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
        blogRepository.save(blog);
        return "displayPost";
    }

}
