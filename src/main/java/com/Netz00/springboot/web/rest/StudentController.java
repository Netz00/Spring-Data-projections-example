package com.Netz00.springboot.web.rest;

import com.Netz00.springboot.domain.Student;
import com.Netz00.springboot.repository.DTOprojection.StudentMin;
import com.Netz00.springboot.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
@Api(value = "api/v1/students", tags = {"Student service"})
@Tag(name = "Student service", description = "Service for handling students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    @ApiOperation(value = "Get all students", response = Iterable.class)
    public List<StudentMin> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    @ApiOperation(value = "Get specific student", response = Student.class)
    public Student getStudent(@PathVariable Long studentId) {
        return studentService.getStudent(studentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create new student", response = Student.class)
    public Student registerNewStudent(@RequestBody Student student) {
        return studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    @ApiOperation(value = "Delete student", response = void.class)
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }


    @PatchMapping(path = "{studentId}")
    @ApiOperation(value = "Update student information", response = Student.class)
    public Student updateStudent(@PathVariable Long studentId,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String email
    ) {
        return studentService.updateStudent(studentId, name, email);
    }

}
