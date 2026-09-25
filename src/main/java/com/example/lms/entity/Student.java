package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY) // ленивая по умолчанию, для явности
    @JoinTable(name = "student_group",
            joinColumns = @JoinColumn(name = "student_id"),//колонка владельца
            inverseJoinColumns = @JoinColumn(name = "group_id")) // колонка другой стороны
    private Set<Group> groups;
}

