package com.example.demo.features.student.dto;

import com.example.demo.features.subject.Subject;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentDTO {
    private int id;
    private String name;
    private LocalDate dob;
    private List<Subject> subjects = new ArrayList<>();
}
