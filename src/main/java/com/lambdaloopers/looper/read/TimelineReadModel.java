package com.lambdaloopers.looper.read;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "read_timelines")
public class TimelineReadModel {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Type(type = "text")
    @Column(name = "timeline")
    private String timeline;

    public TimelineReadModel() {
    }

    public TimelineReadModel(UUID userId, String timeline) {
        this.userId = userId;
        this.timeline = timeline;
    }
}
