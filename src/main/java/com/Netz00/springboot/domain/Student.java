package com.Netz00.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "email")
        })
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @ApiModelProperty(notes = "Name of student", name = "name", required = true, value = "John")
    private String name;

    @Column(
            name = "email",
            nullable = false
    )
    @ApiModelProperty(notes = "Email of student", name = "email", required = true, value = "john@example.com")
    private String email;

    @Column(
            name = "dob",
            nullable = false
    )
    @ApiModelProperty(notes = "Student's date of birth.", name = "dob", required = true, value = "01.01.1999.")
    private LocalDate dob;

    @Transient
    @ApiModelProperty(notes = "Age of student", name = "age", required = false, value = "15")
    private Integer age;

    // -------------- RELATIONSHIPS --------------

    //@JsonIgnore
    @JsonIgnoreProperties(value = {"enrolledStudents","teacher"})
    @ManyToMany(mappedBy = "enrolledStudents",cascade = CascadeType.REMOVE)
    @ApiModelProperty(notes = "Student subjects", name = "subjects", required = false, value = "{subject1, subject2, subject3}")
    private Set<Subject> subjects = new HashSet<>();


    // -------------------------------------------


    public Student() {
    }

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", dob=" + dob + ", age=" + age + '}';
    }
}
