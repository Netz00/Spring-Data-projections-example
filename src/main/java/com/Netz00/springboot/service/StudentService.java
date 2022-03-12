package com.Netz00.springboot.service;

import com.Netz00.springboot.domain.Student;
import com.Netz00.springboot.repository.StudentRepository;
import com.Netz00.springboot.repository.SubjectRepository;
import com.Netz00.springboot.service.exception.EmailAlreadyUsedException;
import com.Netz00.springboot.service.exception.UserDoesNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new EmailAlreadyUsedException();
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) throw new UserDoesNotExistsException(studentId);
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public Student updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UserDoesNotExistsException(studentId));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new EmailAlreadyUsedException();
            }

            student.setEmail(email);
        }

        return student;
    }

    public Student getStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UserDoesNotExistsException(studentId));
        
        return student;
    }


}
