package ru.parhomych.spring01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.parhomych.spring01.dao.StudentDAO;
import ru.parhomych.spring01.model.Student;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDAO studentDAO;

    @Override
    public Student findStudentById(int studentId) {
        return studentDAO.getStudentById(studentId);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDAO.getAllStudents();
    }

    @Override
    public Student addNewStudent(Student student) {
        return studentDAO.addNewStudent(student);
    }

    @Override
    public Student editStudent(Student student) {
        return studentDAO.updateStudent(student);
    }

}
