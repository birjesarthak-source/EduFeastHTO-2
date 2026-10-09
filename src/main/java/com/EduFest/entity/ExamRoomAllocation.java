package com.EduFest.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "exam_room_allocations",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"exam_id", "room_id"}
    )
)
public class ExamRoomAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "room_id")
    private Classroom room;

    /*
     * Faculty assigned to supervise this exam room
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Column(nullable = false)
    private int allocatedStudents;

    @Column(nullable = false, length = 30)
    private String allocationStatus = "CONFIRMED";

    public ExamRoomAllocation() {
    }

    public ExamRoomAllocation(Exam e, Classroom r, int n) {
        exam = e;
        room = r;
        allocatedStudents = n;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam v) {
        exam = v;
    }

    public Classroom getRoom() {
        return room;
    }

    public void setRoom(Classroom v) {
        room = v;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public int getAllocatedStudents() {
        return allocatedStudents;
    }

    public void setAllocatedStudents(int v) {
        allocatedStudents = v;
    }

    public String getAllocationStatus() {
        return allocationStatus;
    }

    public void setAllocationStatus(String v) {
        allocationStatus = v;
    }
}