
package com.EduFest.entity;

import jakarta.persistence.*;

@Entity
@Table(name="exam_student_allocations",
       uniqueConstraints={
           @UniqueConstraint(name="uk_exam_student",columnNames={"exam_id","student_id"}),
           @UniqueConstraint(name="uk_exam_room_seat",columnNames={"exam_id","room_id","seat_number"})
       })
public class ExamStudentAllocation {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="exam_id") private Exam exam;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="student_id") private Student student;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="room_id") private Classroom room;
    @Column(name="seat_number",nullable=false) private int seatNumber;
    public ExamStudentAllocation(){}
    public ExamStudentAllocation(Exam e,Student s,Classroom r,int seat){exam=e;student=s;room=r;seatNumber=seat;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Exam getExam(){return exam;} public void setExam(Exam v){exam=v;}
    public Student getStudent(){return student;} public void setStudent(Student v){student=v;}
    public Classroom getRoom(){return room;} public void setRoom(Classroom v){room=v;}
    public int getSeatNumber(){return seatNumber;} public void setSeatNumber(int v){seatNumber=v;}
}
