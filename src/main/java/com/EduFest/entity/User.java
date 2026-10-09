package com.EduFest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, length=100) private String name;
    @Column(nullable=false, unique=true, length=120) private String email;
    @Column(nullable=false, length=255) private String password;
    @Column(nullable=false, length=30) private String role;

    public User() {}
    public User(String name, String email, String password, String role) {
        this.name=name; this.email=email; this.password=password; this.role=role;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public String getRole(){return role;} public void setRole(String v){role=v;}
}
