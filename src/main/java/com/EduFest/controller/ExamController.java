
package com.EduFest.controller;

import com.EduFest.entity.Exam;
import com.EduFest.entity.ExamRoomAllocation;
import com.EduFest.entity.ExamStudentAllocation;
import com.EduFest.entity.Faculty;
import com.EduFest.repository.DepartmentRepository;
import com.EduFest.repository.ExamRoomAllocationRepository;
import com.EduFest.repository.ExamStudentAllocationRepository;
import com.EduFest.service.ExamAllocationService;
import com.EduFest.service.ExamService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/exams")
public class ExamController {

    private final ExamService exams;
    private final ExamAllocationService allocator;
    private final DepartmentRepository departments;
    private final ExamStudentAllocationRepository studentAllocations;
    private final ExamRoomAllocationRepository examRoomAllocations;

    public ExamController(
            ExamService exams,
            ExamAllocationService allocator,
            DepartmentRepository departments,
            ExamStudentAllocationRepository studentAllocations,
            ExamRoomAllocationRepository examRoomAllocations) {

        this.exams = exams;
        this.allocator = allocator;
        this.departments = departments;
        this.studentAllocations = studentAllocations;
        this.examRoomAllocations = examRoomAllocations;
    }

    @GetMapping
    public String page(Model model) {
        model.addAttribute("exams", exams.findAll());
        model.addAttribute("exam", new Exam());
        model.addAttribute("departments", departments.findAll());
        return "exams";
    }

    @PostMapping
    public String save(@ModelAttribute Exam exam) {
        exams.save(exam);
        return "redirect:/exams";
    }

    @PostMapping("/{id}/allocate")
    public String allocate(@PathVariable Long id, Model model) {
        try {
            Exam exam = exams.get(id);

            List<ExamStudentAllocation> allocations =
                    allocator.allocateStudentsToRooms(exam);

            addSeatingData(exam, allocations, model);
            model.addAttribute("success", "Students allocated successfully.");

        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("exam", exams.get(id));
            model.addAttribute("allocations", List.of());
            model.addAttribute("roomFaculty", Map.of());
        }

        return "exam-allocation";
    }

    @GetMapping("/{id}/allocation")
    public String viewAllocation(@PathVariable Long id, Model model) {
        try {
            Exam exam = exams.get(id);

            List<ExamStudentAllocation> allocations =
                    studentAllocations
                            .findByExamOrderByRoomRoomNumberAscSeatNumberAsc(exam);

            addSeatingData(exam, allocations, model);

            if (allocations.isEmpty()) {
                model.addAttribute(
                        "error",
                        "No saved seating allocations found for this exam. Generate seating first."
                );
            }

        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("allocations", List.of());
            model.addAttribute("roomFaculty", Map.of());
        }

        return "exam-allocation";
    }

    private void addSeatingData(
            Exam exam,
            List<ExamStudentAllocation> allocations,
            Model model) {

        Map<Long, Faculty> roomFaculty = new HashMap<>();

        List<ExamRoomAllocation> roomAllocations =
                examRoomAllocations.findByExam(exam);

        for (ExamRoomAllocation allocation : roomAllocations) {
            if (allocation.getRoom() != null &&
                    allocation.getFaculty() != null) {

                roomFaculty.put(
                        allocation.getRoom().getId(),
                        allocation.getFaculty()
                );
            }
        }

        model.addAttribute("exam", exam);
        model.addAttribute("allocations", allocations);
        model.addAttribute("roomFaculty", roomFaculty);
    }
}
