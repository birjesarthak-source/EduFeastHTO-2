package com.EduFest.repository;
import com.EduFest.entity.Student; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface StudentRepository extends JpaRepository<Student,Long>{ Optional<Student> findByUsn(String usn); List<Student> findBySemesterAndDepartmentId(int semester,Long departmentId); }
