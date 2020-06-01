package com.accenture.StudentApp2.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Course")
public class Course {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private String teacher;
    private int numberOfLectures;

    @ManyToMany
    private Set<Student> students;


}
