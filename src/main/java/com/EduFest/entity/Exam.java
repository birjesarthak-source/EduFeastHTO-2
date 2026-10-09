package com.EduFest.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name="exams")
public class Exam {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=120) private String examName;
    @Column(nullable=false,length=50) private String subjectCode;
    @Column(nullable=false) private LocalDate examDate;
    @Column(nullable=false) private LocalTime startTime;
    @Column(nullable=false) private LocalTime endTime;
    @Column(nullable=false) private int semester;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="department_id") private Department department;
    public Exam(){}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getExamName(){return examName;} public void setExamName(String v){examName=v;}
    public String getSubjectCode(){return subjectCode;} public void setSubjectCode(String v){subjectCode=v;}
    public LocalDate getExamDate(){return examDate;} public void setExamDate(LocalDate v){examDate=v;}
    public LocalTime getStartTime(){return startTime;} public void setStartTime(LocalTime v){startTime=v;}
    public LocalTime getEndTime(){return endTime;} public void setEndTime(LocalTime v){endTime=v;}
    public int getSemester(){return semester;} public void setSemester(int v){semester=v;}
    public Department getDepartment(){return department;} public void setDepartment(Department v){department=v;}
}
