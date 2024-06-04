package com.mustafa.college_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", unique = true)
    private String uuid;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department")
    private Set<Instructor> instructors;

    @OneToMany(mappedBy = "department")
    private Set<Lesson> lessons;

    @OneToMany(mappedBy = "department")
    private Set<Student> students;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @EqualsAndHashCode.Exclude
    private Faculty faculty;
}
