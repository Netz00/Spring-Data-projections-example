package com.Netz00.springboot.repository;

import com.Netz00.springboot.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    //@Query("SELECT e FROM Subject e JOIN student_enrolled d ON e.id = d.subject_id WHERE d.student_id = :studentId")


//    @Query(
//            value = "SELECT * FROM subject JOIN student_enrolled ON subject.id = student_enrolled.subject_id WHERE student_enrolled.student_id = :studentId",
//            nativeQuery = true
//    )
//    Set<Subject> findAllSubjectsByStudentId(@Param("studentId") Long studentId);

    @Query("select s from Subject s inner join s.enrolledStudents enrolledStudents where enrolledStudents.id = :id")
    @Nullable
    Set<Subject> findAllStudentSubjects(@Param("id") @NonNull Long id);


}
