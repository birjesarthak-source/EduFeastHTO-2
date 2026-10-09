package com.EduFest.repository;

import com.EduFest.entity.Classroom;
import com.EduFest.entity.AvailabilityStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository
        extends JpaRepository<Classroom, Long> {

    List<Classroom> findByAvailabilityStatus(
            AvailabilityStatus availabilityStatus
    );
}