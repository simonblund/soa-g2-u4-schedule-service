package com.g2.scheduleservice.infrastructure.db;

import com.g2.scheduleservice.domain.CourseOccasion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseOccasionRepository extends CrudRepository<CourseOccasion, Long> {

}
