package com.lambdaloopers.looper.infrastructure.http;

import com.lambdaloopers.looper.application.DislikeLoopHandler;
import com.lambdaloopers.looper.application.LikeLoopHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DislikeLoopController {

    private final DislikeLoopHandler dislikeLoopHandler;

    public DislikeLoopController(DislikeLoopHandler dislikeLoopHandler) {
        this.dislikeLoopHandler = dislikeLoopHandler;
    }

    @PostMapping("/loops/{loopId}/dislike/{username}")
    public ResponseEntity<Object> post(@PathVariable String loopId, @PathVariable String username) {
        dislikeLoopHandler.handle(loopId, username);
        return ResponseEntity.ok().body("Success!");
    }
}
