package com.example.demo.features.student;

import com.example.demo.errors.RequestError;
import com.example.demo.features.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getStudents() {
        return repository.findAll();
    }

    public Student addNewStudent(Student student) {
        Optional<Student> studentOptional = repository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent() && student.getEmail() != null) {
            throw new RequestError(409, "Email already taken.");
        }
        return repository.save(student);
    }

    public void deleteStudentById(long id) {
        boolean isExist = repository.existsById(id);
        if (!isExist) {
            throw new RequestError(404, "Student not found.");
        }
        repository.deleteById(id);
    }

    @Transactional
    public Student updateStudent(long id, String name, String email) {
        Optional<Student> student = repository.findById(id);
        if (student.isEmpty()) {
            throw new RequestError(404, "Student not found.");
        }
        student.get().setName(name);

        if (email != null && !email.isEmpty()) {
         /*   Optional<Student> studentOptional = repository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new RequestError(409, "Email already taken.");
            }*/
            student.get().setEmail(email);
        }
        return student.get();
    }


    public Optional<Student> findOne(Long studentId) {
        return repository.findById(studentId);
    }
}
