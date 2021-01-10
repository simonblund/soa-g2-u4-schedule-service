package com.g2.scheduleservice.infrastructure.db;

import com.g2.scheduleservice.domain.Resource;
import com.g2.scheduleservice.domain.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long> {
    @Override
    List<Resource> findAll();

}
