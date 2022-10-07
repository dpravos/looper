package com.lambdaloopers.looper.domain.loop;

import org.springframework.context.ApplicationEvent;

public class LoopLiked extends ApplicationEvent {

    private String loopId;
    private String userId;

    public LoopLiked(Object source, String loopId, String userId) {
        super(source);


        this.loopId = loopId;
        this.userId = userId;
    }

    public String getLoopId() {
        return loopId;
    }

    public String getUserId() {
        return userId;
    }
}
