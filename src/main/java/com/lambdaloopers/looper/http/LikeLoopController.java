package com.lambdaloopers.looper.http;

import com.lambdaloopers.looper.application.LikeLoopHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeLoopController {

    private final LikeLoopHandler likeLoopHandler;

    public LikeLoopController(LikeLoopHandler likeLoopHandler) {
        this.likeLoopHandler = likeLoopHandler;
    }

    @PostMapping("/loops/{loopId}/like/{username}")
    public ResponseEntity<Object> post(@PathVariable String loopId, @PathVariable String username) {
        likeLoopHandler.handle(loopId, username);
        return ResponseEntity.ok().body("Success!");
    }
}
