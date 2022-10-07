package com.lambdaloopers.looper.application;

import com.lambdaloopers.looper.domain.loop.Loop;
import com.lambdaloopers.looper.domain.loop.LoopRepository;
import com.lambdaloopers.looper.domain.timeline.Timeline;
import com.lambdaloopers.looper.domain.user.User;
import com.lambdaloopers.looper.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class GetTimelineHandler {

    private final UserRepository userRepository;
    private final LoopRepository loopRepository;

    public GetTimelineHandler(UserRepository userRepository, LoopRepository loopRepository) {
        this.userRepository = userRepository;
        this.loopRepository = loopRepository;
    }

    public Timeline handle(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        ArrayList<Loop> loops = new ArrayList<>();

        user.getFollowing().forEach(followee -> {
            List<Loop> followeeLoops = loopRepository.findAllByAuthorOrderByPublishedAtDesc(followee);
            loops.addAll(followeeLoops);
        });

        loops.sort(Comparator.comparing(Loop::getPublishedAt).reversed());

        return new Timeline(loops);
    }
}
