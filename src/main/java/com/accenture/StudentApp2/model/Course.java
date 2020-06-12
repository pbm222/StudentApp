package com.accenture.StudentApp2.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor
@NoArgsConstructor
@Table(name = "Course")
public class Course {

    @Id @GeneratedValue
    private Long id;
    private String title;
    private String teacher;
    private int numberOfLectures;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Student> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return numberOfLectures == course.numberOfLectures &&
                Objects.equals(id, course.id) &&
                Objects.equals(title, course.title) &&
                Objects.equals(teacher, course.teacher) &&
                Objects.equals(students, course.students);
    }

    //---IMPORTANT! Set both sides of the table
    public void addStudent(Student student){
        students.add(student);
        student.getCourses().add(this);
    }

}
