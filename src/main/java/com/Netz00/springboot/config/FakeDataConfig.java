package com.Netz00.springboot.config;

import com.Netz00.springboot.domain.Student;
import com.Netz00.springboot.domain.Teacher;
import com.Netz00.springboot.repository.StudentRepository;
import com.Netz00.springboot.domain.Subject;
import com.Netz00.springboot.domain.User;
import com.Netz00.springboot.repository.SubjectRepository;
import com.Netz00.springboot.repository.TeacherRepository;
import com.Netz00.springboot.repository.UserRepository;
import org.h2.server.web.WebServlet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.OCTOBER;

@Configuration
public class FakeDataConfig {


    // Enable H2 console inside browser
    @Bean
    ServletRegistrationBean h2ServletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }


    // Run some commands after application starts
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, UserRepository userRepository) {
        return args -> {


            studentRepository.saveAll(List.of(
                    new Student(
                            "Netz",
                            "netzz@gmail.com",
                            LocalDate.of(2000, OCTOBER, 11)
                    ), new Student(
                            "Alex",
                            "alex@gmail.com",
                            LocalDate.of(2000, OCTOBER, 12)
                    ), new Student(
                            "John",
                            "john@gmail.com",
                            LocalDate.of(2000, OCTOBER, 13)
                    ), new Student(
                            "Edgar",
                            "edgar@gmail.com",
                            LocalDate.of(2000, OCTOBER, 14)
                    ), new Student(
                            "Luke",
                            "luke@gmail.com",
                            LocalDate.of(2000, OCTOBER, 15)
                    )
            ));


            teacherRepository.saveAll(List.of(
                    new Teacher(
                            "Netz"
                    ), new Teacher(
                            "Alex"
                    ), new Teacher(
                            "John"
                    ), new Teacher(
                            "Edgar"
                    ), new Teacher(
                            "Luke"
                    )
            ));


            subjectRepository.saveAll(List.of(
                    new Subject(
                            "Mathematics",
                            7
                    ), new Subject(
                            "Programming",
                            7
                    ), new Subject(
                            "Electronics",
                            7
                    ), new Subject(
                            "Web programming",
                            7
                    )
            ));


            User user = new User();
            user.setUserName("admin");
            user.setPassword("admin");
            user.setActive(true);
            user.setRoles("ROLE_ADMIN");

            userRepository.save(user);


        };
    }

}
