package com.EduFest.repository;
import com.EduFest.entity.Exam; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ExamRepository extends JpaRepository<Exam,Long>{ List<Exam> findAllByOrderByExamDateAscStartTimeAsc(); }
