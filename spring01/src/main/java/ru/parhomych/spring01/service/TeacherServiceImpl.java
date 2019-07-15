package ru.parhomych.spring01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.parhomych.spring01.dao.TeacherDAO;
import ru.parhomych.spring01.model.Teacher;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherDAO teacherDAO;

    @Override
    public ResponseEntity<Teacher> findTeacherById(int teacherId) {
        Teacher teacher = teacherDAO.getTeacherById(teacherId);
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<Teacher>> findAllTeachers() {
        List<Teacher> teachers = teacherDAO.getAllTeachers();
        if (teachers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(teachers, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Teacher> addNewTeacher(Teacher teacher) {
        Teacher teacherToReturn = teacherDAO.addNewTeacher(teacher);
        if (teacherToReturn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(teacherToReturn, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Teacher> editTeacher(Teacher teacher) {
        Teacher teacherToReturn = teacherDAO.updateTeacher(teacher);
        if (teacherToReturn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(teacherToReturn, HttpStatus.OK);
        }
    }

    @Override
    public Boolean removeTeacher(int teacherId) {
        if (teacherDAO.getTeacherById(teacherId) == null) {
            return false;
        } else {
            return teacherDAO.deleteTeacherById(teacherId);
        }
    }
}
