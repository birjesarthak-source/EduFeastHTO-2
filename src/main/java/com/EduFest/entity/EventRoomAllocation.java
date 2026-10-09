
package com.EduFest.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "event_room_allocations",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_event_room",
            columnNames = {"event_id", "room_id"}
        )
    }
)
public class EventRoomAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Classroom room;

    @Column(name = "allocated_capacity", nullable = false)
    private int allocatedCapacity;

    @Column(name = "allocation_status", nullable = false, length = 30)
    private String allocationStatus = "CONFIRMED";

    public EventRoomAllocation() {
    }

    public EventRoomAllocation(Event event, Classroom room, int capacity) {
        this.event = event;
        this.room = room;
        this.allocatedCapacity = capacity;
        this.allocationStatus = "CONFIRMED";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Classroom getRoom() {
        return room;
    }

    public void setRoom(Classroom room) {
        this.room = room;
    }

    public int getAllocatedCapacity() {
        return allocatedCapacity;
    }

    public void setAllocatedCapacity(int allocatedCapacity) {
        this.allocatedCapacity = allocatedCapacity;
    }

    public String getAllocationStatus() {
        return allocationStatus;
    }

    public void setAllocationStatus(String allocationStatus) {
        this.allocationStatus = allocationStatus;
    }
}
