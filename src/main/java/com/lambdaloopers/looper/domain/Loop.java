package com.lambdaloopers.looper.domain;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "loops")
public class Loop {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "message")
    private String message;

    @ManyToMany(mappedBy = "liked")
    private Set<User> likes;

    @Column(name = "published_at")
    private Instant publishedAt;

    public Loop() {
    }

    public Loop(UUID id, User author, String message, Set<User> likes, Instant publishedAt) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.likes = likes;
        this.publishedAt = publishedAt;
    }

    public int amountOfLikes() {
        return likes.size();
    }

    public UUID getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }
}
