package ru.parhomych.spring01.dao;

import ru.parhomych.spring01.model.Teacher;

import java.util.List;

public interface TeacherDAO {

    Teacher getTeacherById(int teacherId);
    List<Teacher> getAllTeachers();
    Teacher addNewTeacher(Teacher teacher);
    Teacher updateTeacher(Teacher teacher);
    Boolean deleteTeacherById(int teacherId);

}
