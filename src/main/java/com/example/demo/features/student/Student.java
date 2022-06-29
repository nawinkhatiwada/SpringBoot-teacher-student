package com.example.demo.features.student;

import com.example.demo.features.subject.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "student_sequence")
    private Long id;

    @NotNull
    @NotBlank(message = "name is required.")
    @Valid
    private String name;

    @NotBlank(message = "Email is required.")
    @Email
    @Valid
    private String email;
    @Transient
    private Integer age;
    @NotNull(message = "Dob is required.")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Valid
    private LocalDate dob;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledStudents")
    private Set<Subject> subjects = new HashSet<>();
}
