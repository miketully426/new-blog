package org.laughcode.newblog.controllers;

import jakarta.validation.Valid;
import org.laughcode.newblog.data.BlogRepository;
import org.laughcode.newblog.data.TagRepository;
import org.laughcode.newblog.data.UserRepository;
import org.laughcode.newblog.models.Blog;
import org.laughcode.newblog.models.Status;
import org.laughcode.newblog.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.metrics.StartupStep;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

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
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute("statuses", Status.values());
        return "newPost";
    }

    @PostMapping("/new-post")
    public String handlePostForm(Model model, @ModelAttribute @Valid Blog blog, Errors errors, @RequestParam List<Integer> tags){
        if(errors.hasErrors()){
            model.addAttribute("blog", blog);
            model.addAttribute("statuses", Status.values());
            model.addAttribute("error message", "ERRORS ARE HAPPENING!!!!!");
            return "newPost";
        }
        List<Tag> allTags = (List<Tag>) tagRepository.findAllById(tags);
        blog.setTags(allTags);
        blogRepository.save(blog);
        return "displayPost";
    }



}
