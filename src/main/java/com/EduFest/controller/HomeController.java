package com.EduFest.controller;
import com.EduFest.repository.*; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.GetMapping;
@Controller public class HomeController {
    private final ClassroomRepository classrooms; private final StudentRepository students; private final EventRepository events; private final ExamRepository exams;
    public HomeController(ClassroomRepository c,StudentRepository s,EventRepository e,ExamRepository x){classrooms=c;students=s;events=e;exams=x;}
    @GetMapping("/") public String home(Model model){
        model.addAttribute("classroomCount",classrooms.count());
        model.addAttribute("studentCount",students.count());
        model.addAttribute("eventCount",events.count());
        model.addAttribute("examCount",exams.count());
        model.addAttribute("events",events.findAllByOrderByEventDateAscStartTimeAsc().stream().limit(5).toList());
        model.addAttribute("exams",exams.findAllByOrderByExamDateAscStartTimeAsc().stream().limit(5).toList());
        return "dashboard";
    }
}
