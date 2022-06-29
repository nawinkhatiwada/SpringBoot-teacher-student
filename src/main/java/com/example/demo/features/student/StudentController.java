package com.example.demo.features.student;

import com.example.demo.common.api.ResponseBody;
import com.example.demo.features.student.dto.StudentDTO;
import com.example.demo.features.student.dto.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.demo.errors.ErrorParser.getErrorMessage;
import static com.example.demo.errors.ErrorParser.getErrorStatusCode;

@RestController
@RequestMapping(path = "/api/v1/student")
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @GetMapping
    public ResponseEntity<ResponseBody> getStudents() {
        try {
            List<StudentDTO> students = studentMapper.map(studentService.getStudents());
            return ResponseEntity.ok(ResponseBody.builder()
                    .message("success")
                    .data(students)
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

    @PostMapping
    public ResponseEntity<ResponseBody> registerNewStudent(@RequestBody @Valid Student student) {
        try {
            StudentDTO addedStudent = studentMapper.map(studentService.addNewStudent(student));
            return ResponseEntity.ok(ResponseBody.builder()
                    .message("Student added successfully.")
                    .data(addedStudent)
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

    @DeleteMapping(path = "{id}")
    public ResponseEntity<ResponseBody> deleteStudentById(@PathVariable("id") Long id) {
        try {
            studentService.deleteStudentById(id);
            return ResponseEntity.ok(ResponseBody.builder()
                    .message("Student deleted successfully.")
                    .data(null)
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

    @PutMapping(path = "{id}")
    public ResponseEntity<ResponseBody> updateStudent(
            @PathVariable("id") long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        try {
            return ResponseEntity.ok(ResponseBody.builder()
                    .message("Student updated successfully.")
                    .data(studentService.updateStudent(id, name, email))
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
}
