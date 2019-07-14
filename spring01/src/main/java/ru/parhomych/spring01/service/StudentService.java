package ru.parhomych.spring01.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.parhomych.spring01.model.Student;

import java.util.List;

public interface StudentService {

    ResponseEntity<Student> findStudentById(int studentId);

    ResponseEntity<List<Student>> findAllStudents();

    ResponseEntity<Student> addNewStudent(Student student);

    ResponseEntity<Student> editStudent(Student student);

    Boolean removeStudent(int studentId);

}
