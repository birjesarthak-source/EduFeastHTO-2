package com.EduFest.entity;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,unique=true,length=30) private String usn;
    @Column(nullable=false,length=100) private String name;
    @Column(nullable=false) private int semester;
    @Column(nullable=false,length=10) private String section;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="department_id") private Department department;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="class_section_id") private ClassSection classSection;
    public Student(){}
    public Student(String usn,String name,int semester,String section,Department d,ClassSection c){
        this.usn=usn;this.name=name;this.semester=semester;this.section=section;this.department=d;this.classSection=c;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getUsn(){return usn;} public void setUsn(String v){usn=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public int getSemester(){return semester;} public void setSemester(int v){semester=v;}
    public String getSection(){return section;} public void setSection(String v){section=v;}
    public Department getDepartment(){return department;} public void setDepartment(Department v){department=v;}
    public ClassSection getClassSection(){return classSection;} public void setClassSection(ClassSection v){classSection=v;}
}
