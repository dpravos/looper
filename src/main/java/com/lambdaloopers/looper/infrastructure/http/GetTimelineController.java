package com.lambdaloopers.looper.infrastructure.http;

import com.lambdaloopers.looper.application.GetTimelineHandler;
import com.lambdaloopers.looper.application.GetTimelineHandlerPeroBien;
import com.lambdaloopers.looper.domain.timeline.Timeline;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetTimelineController {

    private final GetTimelineHandlerPeroBien getTimelineHandler;

    public GetTimelineController(GetTimelineHandlerPeroBien getTimelineHandler) {
        this.getTimelineHandler = getTimelineHandler;
    }

    @GetMapping("/timeline/{username}")
    public @ResponseBody Response get(@PathVariable String username) {

        Timeline timeline = getTimelineHandler.handle(username);

        return new Response(timeline);
    }

    private static class Response {

        private final Timeline timeline;

        public Response(Timeline timeline) {
            this.timeline = timeline;
        }

        public Timeline getTimeline() {
            return timeline;
        }
    }
}
