package com.EduFest.controller;
import com.EduFest.entity.Classroom; import com.EduFest.entity.RoomType; import com.EduFest.service.ClassroomService;
import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassroomService service; public ClassroomController(ClassroomService s){service=s;}
    @GetMapping public String page(Model m){m.addAttribute("classrooms",service.findAll());m.addAttribute("classroom",new Classroom());m.addAttribute("types",RoomType.values());return "classrooms";}
    @PostMapping public String save(@ModelAttribute Classroom c){service.save(c);return "redirect:/classrooms";}
    @GetMapping("/delete/{id}") public String delete(@PathVariable Long id){service.delete(id);return "redirect:/classrooms";}
}
