package com.lambdaloopers.looper.read;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TimelineReadModelRepository extends JpaRepository<TimelineReadModel, UUID> {
}
