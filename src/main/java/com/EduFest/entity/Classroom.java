package com.EduFest.entity;

import jakarta.persistence.*;

@Entity
@Table(name="classrooms")
public class Classroom {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,unique=true,length=20) private String roomNumber;
    @Column(nullable=false,length=80) private String building;
    @Column(nullable=false) private int floor;
    @Column(nullable=false) private int capacity;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private RoomType roomType;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private AvailabilityStatus availabilityStatus=AvailabilityStatus.AVAILABLE;
    public Classroom(){}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getRoomNumber(){return roomNumber;} public void setRoomNumber(String v){roomNumber=v;}
    public String getBuilding(){return building;} public void setBuilding(String v){building=v;}
    public int getFloor(){return floor;} public void setFloor(int v){floor=v;}
    public int getCapacity(){return capacity;} public void setCapacity(int v){capacity=v;}
    public RoomType getRoomType(){return roomType;} public void setRoomType(RoomType v){roomType=v;}
    public AvailabilityStatus getAvailabilityStatus(){return availabilityStatus;} public void setAvailabilityStatus(AvailabilityStatus v){availabilityStatus=v;}
}
