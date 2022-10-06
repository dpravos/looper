package com.lambdaloopers.looper.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Timeline {

    private final List<TimelineLoop> loops;
    public Timeline(List<Loop> loops) {
        this.loops = loops.stream().map(TimelineLoop::new).collect(Collectors.toList());
    }

    public List<TimelineLoop> getLoops() {
        return loops;
    }
}
