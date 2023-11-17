package org.laughcode.newblog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Blog {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Title cannot be blank.")
    @Size(min = 10, max = 40, message = "Title must be between 10 and 40 characters.")
    private String title;

    @NotBlank(message = "Content cannot be blank.")
    @Size(min = 5)
    private String content;
    private LocalDate time;
    private Status status;

    @ManyToOne
    private User user;

    public Blog(){
        this.time = LocalDate.now();
    }

    public Blog(String title, String content, Status status) {
        this();
        this.title = title;
        this.content = content;
        this.status = status;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
