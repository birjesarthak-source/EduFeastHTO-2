package com.EduFest.entity;

import jakarta.persistence.*;

@Entity
@Table(name="class_sections",
       uniqueConstraints=@UniqueConstraint(columnNames={"class_name","semester","section","department_id"}))
public class ClassSection {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="class_name",nullable=false,length=80) private String className;
    @Column(nullable=false) private int semester;
    @Column(nullable=false,length=10) private String section;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="department_id") private Department department;
    @Column(nullable=false) private int studentCount;
    public ClassSection(){}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getClassName(){return className;} public void setClassName(String v){className=v;}
    public int getSemester(){return semester;} public void setSemester(int v){semester=v;}
    public String getSection(){return section;} public void setSection(String v){section=v;}
    public Department getDepartment(){return department;} public void setDepartment(Department v){department=v;}
    public int getStudentCount(){return studentCount;} public void setStudentCount(int v){studentCount=v;}
    public String getDisplayName(){return className+" • Sem "+semester+" • "+section;}
}
