package com.example.demo.features.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository repository;

    @Autowired
    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }

    public Subject createNewSubject(Subject subject) {
        return repository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return repository.findAll();
    }

    public Optional<Subject> getASubject(Long subjectId) {
        return repository.findById(subjectId);
    }
}
