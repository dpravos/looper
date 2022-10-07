package com.lambdaloopers.looper.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaloopers.looper.domain.loop.Loop;
import com.lambdaloopers.looper.domain.loop.LoopLiked;
import com.lambdaloopers.looper.domain.loop.LoopRepository;
import com.lambdaloopers.looper.domain.timeline.Timeline;
import com.lambdaloopers.looper.domain.user.User;
import com.lambdaloopers.looper.domain.user.UserRepository;
import com.lambdaloopers.looper.read.TimelineReadModel;
import com.lambdaloopers.looper.read.TimelineReadModelRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class LoopLikedTimelineUpdater implements ApplicationListener<LoopLiked> {
    private final GetTimelineHandler getTimelineHandler;
    private final TimelineReadModelRepository timelineReadModelRepository;
    private final ObjectMapper objectMapper;
    private final LoopRepository loopRepository;
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(LoopLiked event) {
        updateTimelines(event.getLoopId());
    }

    public LoopLikedTimelineUpdater(GetTimelineHandler getTimelineHandler, TimelineReadModelRepository timelineReadModelRepository, ObjectMapper objectMapper, LoopRepository loopRepository, UserRepository userRepository) {
        this.getTimelineHandler = getTimelineHandler;
        this.timelineReadModelRepository = timelineReadModelRepository;
        this.objectMapper = objectMapper;
        this.loopRepository = loopRepository;
        this.userRepository = userRepository;
    }

    private void updateTimelines(String loopId) {

        Loop loop = loopRepository.findById(UUID.fromString(loopId)).orElseThrow();

        List<User> followers = userRepository.findAllByFollowingContaining(loop.getAuthor());

        List<TimelineReadModel> timelines = followers
                .stream()
                .map(this::getTimelineUpdated)
                .toList();

        timelineReadModelRepository.saveAll(timelines);
    }

    private TimelineReadModel getTimelineUpdated(User follower) {
        Timeline timeline = getTimelineHandler.handle(follower.getUsername());
        String serializedTimeline = serializeTimeline(timeline);
        System.out.println("Follower ID: " + follower.getId().toString());

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