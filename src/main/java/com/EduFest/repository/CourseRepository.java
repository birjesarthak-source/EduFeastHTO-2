package com.EduFest.repository;
import com.EduFest.entity.Course; import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseRepository extends JpaRepository<Course,Long>{}
