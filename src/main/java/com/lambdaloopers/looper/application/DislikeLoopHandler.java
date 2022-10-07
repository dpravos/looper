package com.lambdaloopers.looper.application;

import com.lambdaloopers.looper.domain.loop.Loop;
import com.lambdaloopers.looper.domain.loop.LoopRepository;
import com.lambdaloopers.looper.domain.user.User;
import com.lambdaloopers.looper.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DislikeLoopHandler {

    private final LoopRepository loopRepository;
    private final UserRepository userRepository;

    public DislikeLoopHandler(LoopRepository loopRepository, UserRepository userRepository) {
        this.loopRepository = loopRepository;
        this.userRepository = userRepository;
    }

    public void handle(String loopId, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Loop loop = loopRepository.findById(UUID.fromString(loopId)).orElseThrow();

        user.dislike(loop);

        userRepository.save(user);
    }
}
