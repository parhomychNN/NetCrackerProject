package ru.parhomych.spring01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.parhomych.spring01.dao.StudentDAO;
import ru.parhomych.spring01.model.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDAO studentDAO;

    @Override
    public ResponseEntity<Student> findStudentById(int studentId) {

        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<List<Student>> findAllStudents() {

        List<Student> students = studentDAO.getAllStudents();
        if (students == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<Student> addNewStudent(Student student) {

        Student studentToReturn = studentDAO.addNewStudent(student);
        if (studentToReturn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(studentToReturn, HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<Student> editStudent(Student student) {

        Student studentToReturn = studentDAO.updateStudent(student);
        if (studentToReturn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(studentToReturn, HttpStatus.OK);
        }

    }

    @Override
    public Boolean removeStudent(int studentId) {

        if (studentDAO.getStudentById(studentId) == null) {
            return false;
        } else {
            return studentDAO.deleteStudentById(studentId);
        }

    }

}
