package com.lambdaloopers.looper.domain.user;

import com.lambdaloopers.looper.domain.loop.Loop;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "author")
    private List<Loop> authored;

    @ManyToMany
    @JoinTable(
            name = "loop_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "loop_id")
    )
    private Set<Loop> liked;

    @ManyToMany
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private Set<User> following;

    public User() {
    }

    private User(UUID id, String username, List<Loop> authored, Set<Loop> liked, Set<User> following) {
        this.id = id;
        this.username = username;
        this.authored = authored;
        this.liked = liked;
        this.following = following;
    }

    public static User register(UUID id, String username) {
        return new User(id, username, new ArrayList<>(), new HashSet<>(), new HashSet<>());
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void follow(User user) {
        if (user.id.equals(id)) {
            throw new IllegalArgumentException("User cannot follow himself");
        }

        following.add(user);
    }

    public void like(Loop loop) {
        liked.add(loop);
    }

    public void dislike(Loop loop) {
        liked.remove(loop);
    }
}
