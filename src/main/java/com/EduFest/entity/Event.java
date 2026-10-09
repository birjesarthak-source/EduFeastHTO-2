package com.EduFest.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name="events")
public class Event {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=120) private String eventName;
    @Column(length=1000) private String description;
    @Column(nullable=false) private LocalDate eventDate;
    @Column(nullable=false) private LocalTime startTime;
    @Column(nullable=false) private LocalTime endTime;
    @Column(nullable=false) private int expectedParticipants;
    @Column(nullable=false,length=60) private String eventType;
    @Column(nullable=false,length=30) private String status="DRAFT";
    @Column(nullable=false,length=30) private String requiredRoomType="CLASSROOM";
    public Event(){}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getEventName(){return eventName;} public void setEventName(String v){eventName=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public LocalDate getEventDate(){return eventDate;} public void setEventDate(LocalDate v){eventDate=v;}
    public LocalTime getStartTime(){return startTime;} public void setStartTime(LocalTime v){startTime=v;}
    public LocalTime getEndTime(){return endTime;} public void setEndTime(LocalTime v){endTime=v;}
    public int getExpectedParticipants(){return expectedParticipants;} public void setExpectedParticipants(int v){expectedParticipants=v;}
    public String getEventType(){return eventType;} public void setEventType(String v){eventType=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public String getRequiredRoomType(){return requiredRoomType;} public void setRequiredRoomType(String v){requiredRoomType=v;}
}
