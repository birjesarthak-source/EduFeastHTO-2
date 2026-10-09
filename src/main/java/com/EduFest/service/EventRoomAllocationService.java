
package com.EduFest.service;

import com.EduFest.entity.Classroom;
import com.EduFest.entity.Event;
import com.EduFest.entity.EventRoomAllocation;
import com.EduFest.repository.ClassroomRepository;
import com.EduFest.repository.EventRepository;
import com.EduFest.repository.EventRoomAllocationRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventRoomAllocationService {

    private final EventRoomAllocationRepository allocationRepository;
    private final EventRepository eventRepository;
    private final ClassroomRepository classroomRepository;

    public EventRoomAllocationService(
            EventRoomAllocationRepository allocationRepository,
            EventRepository eventRepository,
            ClassroomRepository classroomRepository) {

        this.allocationRepository = allocationRepository;
        this.eventRepository = eventRepository;
        this.classroomRepository = classroomRepository;
    }

    // Allocate a classroom to an event
    @Transactional
    public EventRoomAllocation allocateRoom(
            Long eventId,
            Long roomId,
            int capacity) {

        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Allocated capacity must be greater than zero.");
        }

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Event not found: " + eventId));

        Classroom room = classroomRepository.findById(roomId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Classroom not found: " + roomId));

        // Prevent duplicate event-room combinations
        boolean alreadyAllocated =
                allocationRepository
                        .existsByEvent_IdAndRoom_Id(eventId, roomId);

        if (alreadyAllocated) {
            throw new IllegalArgumentException(
                    "This classroom is already allocated to this event.");
        }

        if (capacity > room.getCapacity()) {
            throw new IllegalArgumentException(
                    "Allocated capacity cannot exceed classroom capacity of "
                            + room.getCapacity() + ".");
        }

        EventRoomAllocation allocation =
                new EventRoomAllocation(event, room, capacity);

        return allocationRepository.saveAndFlush(allocation);
    }

    // Get all event-room allocations
    @Transactional(readOnly = true)
    public List<EventRoomAllocation> getAllAllocations() {
        return allocationRepository.findAll();
    }

    // Get allocations for one event
    @Transactional(readOnly = true)
    public List<EventRoomAllocation> getAllocationsByEvent(
            Long eventId) {

        if (!eventRepository.existsById(eventId)) {
            throw new IllegalArgumentException(
                    "Event not found: " + eventId);
        }

        return allocationRepository.findByEvent_Id(eventId);
    }

    // Get allocations for one classroom
    @Transactional(readOnly = true)
    public List<EventRoomAllocation> getAllocationsByRoom(
            Long roomId) {

        if (!classroomRepository.existsById(roomId)) {
            throw new IllegalArgumentException(
                    "Classroom not found: " + roomId);
        }

        return allocationRepository.findByRoom_Id(roomId);
    }

    // Find one allocation
    @Transactional(readOnly = true)
    public EventRoomAllocation getAllocationById(Long allocationId) {

        return allocationRepository.findById(allocationId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Allocation not found: " + allocationId));
    }

    // Update an existing allocation without inserting a duplicate
    @Transactional
    public EventRoomAllocation updateAllocation(
            Long allocationId,
            Long eventId,
            Long roomId,
            int capacity,
            String status) {

        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Allocated capacity must be greater than zero.");
        }

        EventRoomAllocation allocation =
                allocationRepository.findById(allocationId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Allocation not found: "
                                                + allocationId));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Event not found: " + eventId));

        Classroom room = classroomRepository.findById(roomId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Classroom not found: " + roomId));

        if (capacity > room.getCapacity()) {
            throw new IllegalArgumentException(
                    "Allocated capacity cannot exceed classroom capacity.");
        }

        // Check whether another allocation already uses this event and room
        allocationRepository.findByEvent_IdAndRoom_Id(eventId, roomId)
                .ifPresent(existing -> {
                    if (!existing.getId().equals(allocationId)) {
                        throw new IllegalArgumentException(
                                "This classroom is already allocated "
                                        + "to this event.");
                    }
                });

        allocation.setEvent(event);
        allocation.setRoom(room);
        allocation.setAllocatedCapacity(capacity);

        if (status != null && !status.isBlank()) {
            allocation.setAllocationStatus(status);
        }

        return allocationRepository.saveAndFlush(allocation);
    }

    // Update allocation status
    @Transactional
    public EventRoomAllocation updateAllocationStatus(
            Long allocationId,
            String status) {

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException(
                    "Allocation status is required.");
        }

        EventRoomAllocation allocation =
                allocationRepository.findById(allocationId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Allocation not found: "
                                                + allocationId));

        allocation.setAllocationStatus(status.trim());

        return allocationRepository.save(allocation);
    }
}
