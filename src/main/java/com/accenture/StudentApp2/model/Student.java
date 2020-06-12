package com.accenture.StudentApp2.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Student")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    //@NonNull @Size(min = 10, max = 11, message = "Insert correct personas kods")
    private String personalCode;
    private String email;

    @ManyToMany(cascade = CascadeType.PERSIST) //Student is the managed side, all updates to course go through student
    @Fetch(value = FetchMode.SELECT)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(personalCode, student.personalCode) &&
                Objects.equals(email, student.email) &&
                Objects.equals(courses, student.courses);
    }

}
