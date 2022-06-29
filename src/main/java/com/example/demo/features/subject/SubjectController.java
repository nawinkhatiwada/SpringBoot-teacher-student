package com.example.demo.features.subject;

import com.example.demo.common.BaseController;
import com.example.demo.common.api.ResponseBody;
import com.example.demo.features.student.Student;
import com.example.demo.features.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.Optional;

import static com.example.demo.errors.ErrorParser.getErrorMessage;
import static com.example.demo.errors.ErrorParser.getErrorStatusCode;

@RestController
@RequestMapping(path = "/api/v1/subjects")
public class SubjectController extends BaseController<ResponseBody> {
    private final SubjectService service;
    private final StudentService studentService;

    @Autowired
    public SubjectController(SubjectService service, StudentService studentService) {
        this.service = service;
        this.studentService = studentService;
    }

    @PostMapping
    ResponseEntity<ResponseBody> createNewSubject(@RequestBody @Valid Subject subject) {
        try {
            return ResponseEntity.ok(ResponseBody.builder()
                    .message("success")
                    .data(service.createNewSubject(subject))
                    .status(HttpStatus.OK.value())
                    .build());
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseBody.builder()
                    .message(getErrorMessage(e))
                    .data(null)
                    .status(getErrorStatusCode(e).value())
                    .build());
        }
    }

    @GetMapping
    ResponseEntity<ResponseBody> getAllSubjects() {
        try {
            return ResponseEntity.ok(ResponseBody.builder()
                    .message("success")
                    .data(service.getAllSubjects())
                    .status(HttpStatus.OK.value())
                    .build());
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseBody.builder()
                    .message(getErrorMessage(e))
                    .data(null)
                    .status(getErrorStatusCode(e).value())
                    .build());
        }
    }

    @PutMapping("{subjectId}/students/{studentId}")
    ResponseEntity<ResponseBody> enrollStudentToSubject(@PathVariable Long subjectId,
                                   @PathVariable Long studentId) {
        Optional<Subject> subject = service.getASubject(subjectId);
        Optional<Student> student = studentService.findOne(studentId);

        if (subject.isEmpty() || student.isEmpty()) {
            return ResponseEntity.ok(ResponseBody.builder()
                    .message("student or subject with given data not found.")
                    .data(null)
                    .status(HttpStatus.NOT_FOUND.value())
                    .build());
        }
        subject.get().getEnrolledStudents().add(student.get());
        return ResponseEntity.ok(ResponseBody.builder()
                .message("success")
                .data(service.createNewSubject(subject.get()))
                .status(HttpStatus.OK.value())
                .build());
    }

    // TODO implement base controller in future
    @Override
    public ResponseEntity<ResponseBody> create() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseBody> put() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseBody> getOne() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseBody> getAll() {
        return null;
    }
}
