package com.EduFest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacultyDashboardController {

    @GetMapping("/faculty/dashboard")
    public String facultyDashboard() {
        return "faculty-dashboard";
    }
}