package com.example.demo.features.teacher;

import com.example.demo.common.api.ResponseBody;
import com.example.demo.features.student.Student;
import com.example.demo.features.subject.Subject;
import com.example.demo.features.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

import static com.example.demo.errors.ErrorParser.getErrorMessage;
import static com.example.demo.errors.ErrorParser.getErrorStatusCode;

@RestController
@RequestMapping(path = "/api/v1/teachers")
public class TeacherController {

    private final TeacherService service;
    private final SubjectService subjectService;

    @Autowired
    public TeacherController(TeacherService service, SubjectService subjectService) {
        this.service = service;
        this.subjectService = subjectService;
    }

    @PostMapping
    ResponseEntity<ResponseBody> createNewSubject(@RequestBody @Valid Teacher teacher) {
        try {
            return ResponseEntity.ok(ResponseBody.builder()
                    .message("success")
                    .data(service.createNewTeacher(teacher))
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
    ResponseEntity<ResponseBody> getAllTeachers() {
        try {
            return ResponseEntity.ok(ResponseBody.builder()
                    .message("success")
                    .data(service.getAllTeachers())
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

    @PutMapping("/{teacherId}/subject/{subjectId}")
    ResponseEntity<ResponseBody> assignTeacherToSubject(@PathVariable Long subjectId,
                                                        @PathVariable Long teacherId) {
        Optional<Subject> subject = subjectService.getASubject(subjectId);
        Optional<Teacher> teacher = service.findOne(teacherId);

        if (subject.isEmpty() || teacher.isEmpty()) {
            return ResponseEntity.ok(ResponseBody.builder()
                    .message("subject or teacher with given data not found.")
                    .data(null)
                    .status(HttpStatus.NOT_FOUND.value())
                    .build());
        }

        subject.get().setTeacher(teacher.get());
        return ResponseEntity.ok(ResponseBody.builder()
                .message("success")
                .data(subjectService.createNewSubject(subject.get()))
                .status(HttpStatus.OK.value())
                .build());
    }
}
