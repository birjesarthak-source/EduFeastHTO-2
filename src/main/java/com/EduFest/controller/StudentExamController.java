package com.EduFest.controller;

import com.EduFest.entity.ExamRoomAllocation;
import com.EduFest.entity.ExamStudentAllocation;
import com.EduFest.entity.Faculty;
import com.EduFest.repository.ExamRepository;
import com.EduFest.repository.ExamRoomAllocationRepository;
import com.EduFest.repository.ExamStudentAllocationRepository;
import com.EduFest.repository.FacultyRepository;
import com.EduFest.service.StudentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentExamController {

    private final StudentService students;
    private final ExamRepository exams;
    private final ExamStudentAllocationRepository allocations;
    private final FacultyRepository facultyRepository;
    private final ExamRoomAllocationRepository examRoomAllocations;

    public StudentExamController(
            StudentService s,
            ExamRepository e,
            ExamStudentAllocationRepository a,
            FacultyRepository f,
            ExamRoomAllocationRepository era) {

        students = s;
        exams = e;
        allocations = a;
        facultyRepository = f;
        examRoomAllocations = era;
    }

    /*
     * =========================================================
     * OPEN FIND MY EXAM ROOM PAGE
     * =========================================================
     */
    @GetMapping("/student-exam-search")
    public String page(Model m) {

        return "student-exam-search";
    }

    /*
     * =========================================================
     * SEARCH STUDENT BY USN
     * =========================================================
     */
    @PostMapping("/student-exam-search")
    public String searchStudent(
            @RequestParam String usn,
            Model m) {

        try {

            var student = students.findByUsn(usn);

            if (student == null) {

                m.addAttribute(
                        "error",
                        "Student with USN " + usn + " not found."
                );

                return "student-exam-search";
            }

            List<ExamStudentAllocation> found =
                    allocations.findAll()
                            .stream()
                            .filter(a ->
                                    a.getStudent() != null
                                    && a.getStudent().getId()
                                    .equals(student.getId()))
                            .toList();

            m.addAttribute("student", student);
            m.addAttribute("studentAllocations", found);
            m.addAttribute("searchType", "student");

        } catch (Exception ex) {

            m.addAttribute(
                    "error",
                    ex.getMessage()
            );
        }

        return "student-exam-search";
    }

    /*
     * =========================================================
     * SEARCH FACULTY BY FACULTY ID
     * =========================================================
     */
    @PostMapping("/faculty-exam-search")
    public String searchFaculty(
            @RequestParam String facultyId,
            Model m) {

        try {

            Faculty faculty =
                    facultyRepository
                            .findByFacultyId(facultyId.trim())
                            .orElse(null);

            if (faculty == null) {

                m.addAttribute(
                        "error",
                        "Faculty ID " + facultyId + " not found."
                );

                m.addAttribute(
                        "searchType",
                        "faculty"
                );

                return "student-exam-search";
            }

            List<ExamRoomAllocation> facultyAllocations =
                    examRoomAllocations
                            .findByFaculty(faculty);

            m.addAttribute(
                    "faculty",
                    faculty
            );

            m.addAttribute(
                    "facultyAllocations",
                    facultyAllocations
            );

            m.addAttribute(
                    "searchType",
                    "faculty"
            );

        } catch (Exception ex) {

            m.addAttribute(
                    "error",
                    ex.getMessage()
            );
        }

        return "student-exam-search";
    }
}