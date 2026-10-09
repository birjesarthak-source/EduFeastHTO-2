package com.EduFest.service;
import com.EduFest.entity.Event; import com.EduFest.repository.EventRepository;
import org.springframework.stereotype.Service; import java.util.*;
@Service public class EventService {
    private final EventRepository repo;
    public EventService(EventRepository repo){this.repo=repo;}
    public List<Event> findAll(){return repo.findAllByOrderByEventDateAscStartTimeAsc();}
    public Event save(Event e){return repo.save(e);}
    public Event get(Long id){return repo.findById(id).orElseThrow();}
}
