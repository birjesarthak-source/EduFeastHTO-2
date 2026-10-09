package com.EduFest.entity;

import jakarta.persistence.*;

@Entity
@Table(name="courses")
public class Course {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,unique=true,length=30) private String courseCode;
    @Column(nullable=false,length=120) private String courseName;
    @Column(nullable=false) private int semester;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="department_id") private Department department;
    public Course(){}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getCourseCode(){return courseCode;} public void setCourseCode(String v){courseCode=v;}
    public String getCourseName(){return courseName;} public void setCourseName(String v){courseName=v;}
    public int getSemester(){return semester;} public void setSemester(int v){semester=v;}
    public Department getDepartment(){return department;} public void setDepartment(Department v){department=v;}
}
