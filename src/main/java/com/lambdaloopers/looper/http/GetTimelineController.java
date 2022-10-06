package com.lambdaloopers.looper.http;

import com.lambdaloopers.looper.application.GetTimelineHandler;
import com.lambdaloopers.looper.domain.Timeline;
import org.springframework.web.bind.annotation.*;

@RestController
public class GetTimelineController {

    private final GetTimelineHandler getTimelineHandler;

    public GetTimelineController(GetTimelineHandler getTimelineHandler) {
        this.getTimelineHandler = getTimelineHandler;
    }

    @GetMapping("/timeline/{username}")
    public @ResponseBody Response post(@PathVariable String username) {

        Timeline timeline= getTimelineHandler.handle(username);

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
