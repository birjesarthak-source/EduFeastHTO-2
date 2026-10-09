package com.EduFest.repository;

import com.EduFest.entity.Classroom;
import com.EduFest.entity.Event;
import com.EduFest.entity.EventRoomAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRoomAllocationRepository
        extends JpaRepository<EventRoomAllocation, Long> {

    List<EventRoomAllocation> findByEvent(Event event);

    List<EventRoomAllocation> findByRoom(Classroom room);
}