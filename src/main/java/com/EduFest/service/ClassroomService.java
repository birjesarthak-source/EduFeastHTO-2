package com.EduFest.service;
import com.EduFest.entity.*; import com.EduFest.repository.ClassroomRepository;
import org.springframework.stereotype.Service; import java.util.*;
@Service public class ClassroomService {
    private final ClassroomRepository repo;
    public ClassroomService(ClassroomRepository repo){this.repo=repo;}
    public List<Classroom> findAll(){return repo.findAll();}
    public List<Classroom> available(){return repo.findByAvailabilityStatus(AvailabilityStatus.AVAILABLE);}
    public Classroom save(Classroom c){return repo.save(c);}
    public void delete(Long id){repo.deleteById(id);}
    public Classroom get(Long id){return repo.findById(id).orElseThrow();}
}
