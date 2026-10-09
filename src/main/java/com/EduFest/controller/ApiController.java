package com.EduFest.controller;
import com.EduFest.entity.*; import com.EduFest.repository.*; import com.EduFest.service.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api")
public class ApiController {
    private final ClassroomRepository rooms; private final EventRepository events; private final ExamRepository exams; private final StudentRepository students;
    private final EventAllocationService eventAllocator; private final ExamAllocationService examAllocator;
    public ApiController(ClassroomRepository r,EventRepository e,ExamRepository x,StudentRepository s,EventAllocationService ea,ExamAllocationService xa){
        rooms=r;events=e;exams=x;students=s;eventAllocator=ea;examAllocator=xa;
    }
    @GetMapping("/classrooms") public List<Classroom> rooms(){return rooms.findAll();}
    @PostMapping("/classrooms") public Classroom addRoom(@RequestBody Classroom c){return rooms.save(c);}
    @PutMapping("/classrooms/{id}") public Classroom updateRoom(@PathVariable Long id,@RequestBody Classroom c){c.setId(id);return rooms.save(c);}
    @DeleteMapping("/classrooms/{id}") public void deleteRoom(@PathVariable Long id){rooms.deleteById(id);}
    @GetMapping("/events") public List<Event> events(){return events.findAllByOrderByEventDateAscStartTimeAsc();}
    @PostMapping("/events") public Event addEvent(@RequestBody Event e){return events.save(e);}
    @GetMapping("/events/{id}") public Event event(@PathVariable Long id){return events.findById(id).orElseThrow();}
    @PostMapping("/events/{id}/allocate") public List<EventRoomAllocation> allocateEvent(@PathVariable Long id){return eventAllocator.allocate(events.findById(id).orElseThrow());}
    @GetMapping("/exams") public List<Exam> exams(){return exams.findAllByOrderByExamDateAscStartTimeAsc();}
    @PostMapping("/exams") public Exam addExam(@RequestBody Exam e){return exams.save(e);}
    @PostMapping("/exams/{id}/allocate") public List<ExamStudentAllocation> allocateExam(@PathVariable Long id){return examAllocator.allocateStudentsToRooms(exams.findById(id).orElseThrow());}
    @GetMapping("/students/{usn}/exam-allocation") public ExamStudentAllocation studentAllocation(@PathVariable String usn){
        Student s=students.findByUsn(usn).orElseThrow();
        return examAllocator.findAllocations(exams.findAll().stream().findFirst().orElseThrow()).stream()
                .filter(a->a.getStudent().getId().equals(s.getId())).findFirst().orElseThrow();
    }
    @GetMapping("/rooms/available") public List<Classroom> available(){return rooms.findByAvailabilityStatus(AvailabilityStatus.AVAILABLE);}
}
