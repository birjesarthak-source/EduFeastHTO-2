
package com.EduFest.repository;

import com.EduFest.entity.EventRoomAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRoomAllocationRepository
        extends JpaRepository<EventRoomAllocation, Long> {

    boolean existsByEvent_IdAndRoom_Id(Long eventId, Long roomId);

    Optional<EventRoomAllocation> findByEvent_IdAndRoom_Id(
            Long eventId, Long roomId);

    List<EventRoomAllocation> findByEvent_Id(Long eventId);

    List<EventRoomAllocation> findByRoom_Id(Long roomId);
}
