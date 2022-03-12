package com.Netz00.springboot.service;

import com.Netz00.springboot.domain.Student;
import com.Netz00.springboot.domain.Subject;
import com.Netz00.springboot.repository.StudentRepository;
import com.Netz00.springboot.repository.TeacherRepository;
import com.Netz00.springboot.service.exception.UserDoesNotExistsException;
import com.Netz00.springboot.domain.Teacher;
import com.Netz00.springboot.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    @Autowired
    public SubjectService(SubjectRepository subjectRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject enrollStudent(Long subjectId, Long studentId) {

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject doesnt exist"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UserDoesNotExistsException(studentId));

        subject.enrollStudent(student);

        return subjectRepository.save(subject);
    }

    public Subject assignTeacherToSubject(Long subjectId, Long teacherId) {

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject doesn't exist"));
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new UserDoesNotExistsException(teacherId));

        subject.assignTeacher(teacher);

        return subjectRepository.save(subject);

    }
}
