package org.laughcode.newblog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class BlogContent {

    @Id
    @GeneratedValue
    private int id;

    private String content;

    @OneToOne(mappedBy = "blogContent")
    private Blog blog;

    public BlogContent(String content, Blog blog) {
        this.content = content;
        this.blog = blog;
    }

    public BlogContent(){}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
