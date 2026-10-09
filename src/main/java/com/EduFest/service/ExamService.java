package com.EduFest.service;
import com.EduFest.entity.Exam; import com.EduFest.repository.ExamRepository;
import org.springframework.stereotype.Service; import java.util.*;
@Service public class ExamService {
    private final ExamRepository repo;
    public ExamService(ExamRepository repo){this.repo=repo;}
    public List<Exam> findAll(){return repo.findAllByOrderByExamDateAscStartTimeAsc();}
    public Exam save(Exam e){return repo.save(e);}
    public Exam get(Long id){return repo.findById(id).orElseThrow();}
}
