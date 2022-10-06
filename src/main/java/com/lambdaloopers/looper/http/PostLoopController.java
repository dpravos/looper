package com.lambdaloopers.looper.http;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostLoopController {

    @PostMapping("/loops")
    public String post(@RequestBody Request request) {
        System.out.println(request);
        return request.message;
    }

    private record Request(String authorId, String loopId, String message) {
    }
}
