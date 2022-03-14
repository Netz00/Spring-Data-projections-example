package com.Netz00.springboot.repository;

import com.Netz00.springboot.domain.Student;
import com.Netz00.springboot.repository.DTOprojection.StudentMin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // SELECT * FROM student WHERE email = ?
    @Query("SELECT s FROM Student s WHERE s.email=?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s")
    List<StudentMin> getAll();

}
