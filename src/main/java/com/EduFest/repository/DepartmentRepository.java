package com.EduFest.repository;
import com.EduFest.entity.Department; import org.springframework.data.jpa.repository.JpaRepository;
public interface DepartmentRepository extends JpaRepository<Department,Long>{}
