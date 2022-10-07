package com.lambdaloopers.looper.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaloopers.looper.domain.timeline.Timeline;
import com.lambdaloopers.looper.domain.user.User;
import com.lambdaloopers.looper.domain.user.UserRepository;
import com.lambdaloopers.looper.read.TimelineReadModel;
import com.lambdaloopers.looper.read.TimelineReadModelRepository;
import org.springframework.stereotype.Service;

@Service
public class GetTimelineHandlerPeroBien {

    private final UserRepository userRepository;
    private final TimelineReadModelRepository timelineReadModelRepository;
    private final ObjectMapper objectMapper;

    public GetTimelineHandlerPeroBien(UserRepository userRepository, TimelineReadModelRepository timelineReadModelRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.timelineReadModelRepository = timelineReadModelRepository;
        this.objectMapper = objectMapper;
    }

    public Timeline handle(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        TimelineReadModel timelineReadModel = timelineReadModelRepository.findById(user.getId()).orElseThrow();

        return deserializeTimeline(timelineReadModel);
    }

    private Timeline deserializeTimeline(TimelineReadModel timelineReadModel) {
        try {
            return objectMapper.readValue(timelineReadModel.getTimeline(), Timeline.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
