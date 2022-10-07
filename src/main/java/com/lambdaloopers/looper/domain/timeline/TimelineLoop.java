package com.lambdaloopers.looper.domain.timeline;

import com.lambdaloopers.looper.domain.loop.Loop;

import java.time.Instant;

public class TimelineLoop {
    private String id;
    private String message;
    private Instant publishedAt;
    private String authorId;
    private String authorUsername;

    public void setId(String id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public void setAmountOfLikes(int amountOfLikes) {
        this.amountOfLikes = amountOfLikes;
    }

    private int amountOfLikes;

    public TimelineLoop() {
    }

    public TimelineLoop(Loop loop) {
        id = loop.getId().toString();
        message = loop.getMessage();
        publishedAt = loop.getPublishedAt();
        authorId = loop.getAuthor().getId().toString();
        authorUsername = loop.getAuthor().getUsername();
        amountOfLikes = loop.amountOfLikes();
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public int getAmountOfLikes() {
        return amountOfLikes;
    }
}
