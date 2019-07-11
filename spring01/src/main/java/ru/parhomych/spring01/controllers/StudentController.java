package ru.parhomych.spring01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.parhomych.spring01.model.Student;
import ru.parhomych.spring01.service.StudentService;
import ru.parhomych.spring01.utils.LearningCenterDataBaseUtil;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable String studentId){

        int studentIdInt = Integer.valueOf(studentId);
        Student student = studentService.findStudentById(studentIdInt);
        if (student == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }

    }

    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){

        List<Student> students = studentService.findAllStudents();
        if (students == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }

    }

    @Produces(MediaType.APPLICATION_JSON)
    @PostMapping("/students/add")
    public ResponseEntity<Student> addNewStudent(@RequestBody Student studentJSON) {

        return new ResponseEntity<Student> (
                studentService.addNewStudent(studentJSON),
                HttpStatus.OK
        );

    }

    @Produces(MediaType.APPLICATION_JSON)
    @PutMapping("/student/")
    public ResponseEntity<Student> editStudent(@RequestBody Student studentJSON) {

        return new ResponseEntity<Student> (
                studentService.editStudent(studentJSON),
                HttpStatus.OK
        );

    }

    @Produces(MediaType.APPLICATION_JSON)
    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<String> deleteStudent (@PathVariable String studentId) {

        int studentIdInt = Integer.valueOf(studentId);
        Boolean statusOfDeleting = studentService.removeStudent(studentIdInt);
        return new ResponseEntity<String>(
                statusOfDeleting.toString(),
                HttpStatus.OK
        );

    }

}
