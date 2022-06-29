package com.example.demo.features.student.dto;

import com.example.demo.features.student.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    List<StudentDTO> map(List<Student> type);
    StudentDTO map(Student type);
}
