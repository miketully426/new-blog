package org.laughcode.newblog.controllers;

import org.laughcode.newblog.data.TagRepository;
import org.laughcode.newblog.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tags")
public class TagController {

    @Autowired
    TagRepository tagRepository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/submit";
    }

    @PostMapping
    public String submit(Model model, String tag){
        tagRepository.save(new Tag(tag));
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/submit";
    }
}
