package org.laughcode.newblog.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Blog {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Title cannot be blank.")
    @Size(min = 10, max = 40, message = "Title must be between 10 and 40 characters.")
    private String title;

    private LocalDate time;
    private Status status;

    @ManyToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private BlogContent blogContent;

    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

    public Blog(){
        this.time = LocalDate.now();
    }

    public Blog(String title, String content, Status status, BlogContent blogContent) {
        this();
        this.title = title;
        this.status = status;
        this.blogContent = blogContent;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getTime() {
        return time;
    }

    public BlogContent getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(BlogContent blogContent) {
        this.blogContent = blogContent;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
