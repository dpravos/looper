package com.lambdaloopers.looper.domain.loop;

import com.lambdaloopers.looper.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoopRepository extends JpaRepository<Loop, UUID> {
    List<Loop> findAllByAuthorOrderByPublishedAtDesc(User author);
}
