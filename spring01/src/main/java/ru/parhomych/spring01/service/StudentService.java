package ru.parhomych.spring01.service;

import org.springframework.stereotype.Service;
import ru.parhomych.spring01.model.Student;

import java.util.List;

public interface StudentService {

    Student findStudentById(int studentId);
    List<Student> findAllStudents();
    Student addNewStudent(Student student);
    Student editStudent(Student student);
    Boolean removeStudent(int studentId);

}
