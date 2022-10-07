package com.lambdaloopers.looper.domain.timeline;

import com.lambdaloopers.looper.domain.loop.Loop;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Timeline {

    private List<TimelineLoop> loops;

    public Timeline() {
        this.loops = new ArrayList<>();
    }

    public Timeline(List<Loop> loops) {
        this.loops = loops.stream().map(TimelineLoop::new).collect(Collectors.toList());
    }

    public List<TimelineLoop> getLoops() {
        return loops;
    }

    public void setLoops(List<TimelineLoop> loops) {
        this.loops = loops;
    }
}
