package com.EduFest.repository;

import com.EduFest.entity.Exam;
import com.EduFest.entity.ExamStudentAllocation;
import com.EduFest.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamStudentAllocationRepository
        extends JpaRepository<ExamStudentAllocation, Long> {

    List<ExamStudentAllocation>
    findByExamOrderByRoomRoomNumberAscSeatNumberAsc(Exam exam);

    Optional<ExamStudentAllocation>
    findByExamAndStudent(Exam exam, Student student);

    List<ExamStudentAllocation>
    findByStudent(Student student);
}