package com.lambdaloopers.looper.domain;

import java.time.Instant;

public class TimelineLoop {
    private final String id;
    private final String message;
    private final Instant publishedAt;
    private final String authorId;
    private final String authorUsername;
    private final int amountOfLikes;

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
