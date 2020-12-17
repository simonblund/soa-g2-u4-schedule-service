package com.g2.scheduleservice.infrastructure.db;

import com.g2.scheduleservice.domain.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    @Override
    List<Teacher> findAll();
}
