package com.Netz00.springboot.web.rest;

import com.Netz00.springboot.domain.Teacher;
import com.Netz00.springboot.repository.DTOprojection.TeacherMin;
import com.Netz00.springboot.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/teachers")
@Api(value = "api/v1/teachers", tags = {"Teacher service"})
@Tag(name = "Teacher service", description = "Service for handling teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @GetMapping
    @ApiOperation(value = "Get all teachers", response = Iterable.class)
    public List<TeacherMin> getTeachers() {
        return teacherService.getTeachers();
    }

    @PostMapping
    @ApiOperation(value = "Create new teacher", response = Teacher.class)
    public Teacher createSubject(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }


}
