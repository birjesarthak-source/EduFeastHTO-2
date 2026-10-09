package com.EduFest.controller;
import com.EduFest.entity.*; import com.EduFest.repository.*; import com.EduFest.service.StudentService;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/students")
public class StudentController {
    private final StudentService service; private final DepartmentRepository departments; private final ClassSectionRepository sections;
    public StudentController(StudentService s,DepartmentRepository d,ClassSectionRepository c){service=s;departments=d;sections=c;}
    @GetMapping public String page(Model m){m.addAttribute("students",service.findAll());m.addAttribute("student",new Student());m.addAttribute("departments",departments.findAll());m.addAttribute("sections",sections.findAll());return "students";}
    @PostMapping public String save(@ModelAttribute Student s){service.save(s);return "redirect:/students";}
}
