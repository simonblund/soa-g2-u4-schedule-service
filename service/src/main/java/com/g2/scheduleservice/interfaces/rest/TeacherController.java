package com.g2.scheduleservice.interfaces.rest;

import com.g2.scheduleservice.api.rest.UrlPaths;
import com.g2.scheduleservice.api.rest.teacher.TeacherListResponse;
import com.g2.scheduleservice.api.rest.teacher.TeacherResponse;
import com.g2.scheduleservice.domain.DomainObjectMapper;
import com.g2.scheduleservice.infrastructure.db.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherRepository repository;

    @GetMapping(UrlPaths.TEACHER_RESOURCE)
    public ResponseEntity<TeacherListResponse> getAll(){
        val teachers = repository.findAll().stream()
                .map(it -> DomainObjectMapper.toTeacherResponse(it))
                .collect(Collectors.toList());

        return ResponseEntity.ok(TeacherListResponse.builder().teachers(teachers).build());
    }

    @GetMapping(UrlPaths.GET_TEACHER)
    public ResponseEntity<TeacherResponse> findOne(@PathVariable long teacherId){
        val teacher = repository.findById(teacherId).get();

        return ResponseEntity.ok(DomainObjectMapper.toTeacherResponse(teacher));
    }

}
