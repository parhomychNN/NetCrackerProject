package ru.parhomych.spring01.dao;

import ru.parhomych.spring01.model.Student;

import java.util.List;

public interface StudentDAO {

    Student getStudentById(int studentId);

    List<Student> getAllStudents();

    Student addNewStudent(Student student);

}
