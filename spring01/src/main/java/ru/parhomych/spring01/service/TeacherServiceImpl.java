package ru.parhomych.spring01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.parhomych.spring01.dao.TeacherDAO;
import ru.parhomych.spring01.model.Teacher;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherDAO teacherDAO;

    @Override
    public Teacher findTeacherById(int teacherId) {
        return teacherDAO.getTeacherById(teacherId);
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherDAO.getAllTeachers();
    }

    @Override
    public Teacher addNewTeacher(Teacher teacher) {
        return teacherDAO.addNewTeacher(teacher);
    }

    @Override
    public Teacher editTeacher(Teacher teacher) {
        return teacherDAO.updateTeacher(teacher);
    }

    @Override
    public Boolean removeTeacher(int teacherId) {
        return teacherDAO.deleteTeacherById(teacherId);
    }

}
