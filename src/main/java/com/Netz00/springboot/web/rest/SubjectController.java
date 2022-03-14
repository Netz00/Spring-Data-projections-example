package com.Netz00.springboot.web.rest;

import com.Netz00.springboot.domain.Subject;
import com.Netz00.springboot.repository.DTOprojection.SubjectMin;
import com.Netz00.springboot.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @GetMapping
    public List<SubjectMin> getStudents() {
        return subjectService.getSubjects();
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @PutMapping("/{subjectId}/students/{studentId}")
    public Subject enrollStudent(
            @PathVariable("subjectId") Long subjectId,
            @PathVariable("studentId") Long studentId
    ) {
        return subjectService.enrollStudent(subjectId,studentId);

    }


    @PutMapping("/{subjectId}/teachers/{teacherId}")
    public Subject assignTeacherToSubject(
            @PathVariable("subjectId") Long subjectId,
            @PathVariable("teacherId") Long teacherId
    ) {
        return subjectService.assignTeacherToSubject(subjectId,teacherId);

    }

}
