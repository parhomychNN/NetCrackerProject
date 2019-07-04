package ru.parhomych.spring01.service;

import ru.parhomych.spring01.model.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher findTeacherById(int teacherId);

    List<Teacher> findAllTeachers();

    Teacher addNewTeacher(Teacher teacher);
}
