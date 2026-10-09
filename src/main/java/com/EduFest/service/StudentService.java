package com.EduFest.service;
import com.EduFest.entity.Student; import com.EduFest.repository.StudentRepository;
import org.springframework.stereotype.Service; import java.util.*;
@Service public class StudentService {
    private final StudentRepository repo;
    public StudentService(StudentRepository repo){this.repo=repo;}
    public List<Student> findAll(){return repo.findAll();}
    public Student save(Student s){return repo.save(s);}
    public Student findByUsn(String usn){return repo.findByUsn(usn).orElseThrow();}
}
