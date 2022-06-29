package com.example.demo.features.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository repository;

    @Autowired
    TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public Teacher createNewTeacher(Teacher teacher) {
        return repository.save(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return repository.findAll();
    }

    public Optional<Teacher> findOne(Long teacherId) {
        return repository.findById(teacherId);
    }
}
