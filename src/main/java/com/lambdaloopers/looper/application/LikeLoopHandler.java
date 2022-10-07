package com.lambdaloopers.looper.application;

import com.lambdaloopers.looper.domain.loop.Loop;
import com.lambdaloopers.looper.domain.loop.LoopLiked;
import com.lambdaloopers.looper.domain.loop.LoopRepository;
import com.lambdaloopers.looper.domain.user.User;
import com.lambdaloopers.looper.domain.user.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LikeLoopHandler {
    private final LoopRepository loopRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public LikeLoopHandler(
            LoopRepository loopRepository,
            UserRepository userRepository,
            ApplicationEventPublisher applicationEventPublisher) {
        this.loopRepository = loopRepository;
        this.userRepository = userRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void handle(String loopId, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Loop loop = loopRepository.findById(UUID.fromString(loopId)).orElseThrow();

        user.like(loop);
        userRepository.save(user);

        LoopLiked loopLiked = new LoopLiked(this, loopId, user.getId().toString());
        applicationEventPublisher.publishEvent(loopLiked);
    }
}
