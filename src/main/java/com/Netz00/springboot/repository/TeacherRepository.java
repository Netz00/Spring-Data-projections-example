package com.Netz00.springboot.repository;

import com.Netz00.springboot.domain.Teacher;
import com.Netz00.springboot.repository.DTOprojection.TeacherMin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT s FROM Teacher s")
    List<TeacherMin> getAll();

}
