package com.accenture.StudentApp2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Student")
@Data @NoArgsConstructor @AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    //@NonNull @Size(min = 10, max = 11, message = "Insert correct personas kods")
    private String personalCode;
    private String email;

    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

}
