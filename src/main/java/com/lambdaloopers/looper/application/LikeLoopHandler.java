package com.lambdaloopers.looper.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaloopers.looper.domain.loop.Loop;
import com.lambdaloopers.looper.domain.loop.LoopRepository;
import com.lambdaloopers.looper.domain.timeline.Timeline;
import com.lambdaloopers.looper.domain.user.User;
import com.lambdaloopers.looper.domain.user.UserRepository;
import com.lambdaloopers.looper.read.TimelineReadModel;
import com.lambdaloopers.looper.read.TimelineReadModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LikeLoopHandler {
    private final LoopRepository loopRepository;
    private final UserRepository userRepository;
    private final TimelineReadModelRepository timelineReadModelRepository;
    private final GetTimelineHandler getTimelineHandler;
    private final ObjectMapper objectMapper;

    public LikeLoopHandler(LoopRepository loopRepository, UserRepository userRepository, TimelineReadModelRepository timelineReadModelRepository, GetTimelineHandler getTimelineHandler, ObjectMapper objectMapper) {
        this.loopRepository = loopRepository;
        this.userRepository = userRepository;
        this.timelineReadModelRepository = timelineReadModelRepository;
        this.getTimelineHandler = getTimelineHandler;
        this.objectMapper = objectMapper;
    }

    public void handle(String loopId, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Loop loop = loopRepository.findById(UUID.fromString(loopId)).orElseThrow();

        user.like(loop);

        userRepository.save(user);

        List<TimelineReadModel> timelines = loop.getAuthor().getFollowers()
                .stream()
                .map(this::getTimelineUpdated)
                .toList();

        timelineReadModelRepository.saveAll(timelines);
    }

    private TimelineReadModel getTimelineUpdated(User follower) {
        Timeline timeline = getTimelineHandler.handle(follower.getUsername());
        String serializedTimeline = serializeTimeline(timeline);

        return new TimelineReadModel(follower.getId(), serializedTimeline);
    }

    private String serializeTimeline(Timeline timeline) {
        try {
            return objectMapper.writeValueAsString(timeline);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
