package com.EduFest.entity;

import jakarta.persistence.*;

@Entity
@Table(name="departments")
public class Department {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=20) private String departmentCode;
    @Column(nullable=false, length=100) private String departmentName;
    public Department(){}
    public Department(String code,String name){departmentCode=code;departmentName=name;}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getDepartmentCode(){return departmentCode;} public void setDepartmentCode(String v){departmentCode=v;}
    public String getDepartmentName(){return departmentName;} public void setDepartmentName(String v){departmentName=v;}
}
