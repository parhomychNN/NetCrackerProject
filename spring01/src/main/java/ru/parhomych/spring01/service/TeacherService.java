package ru.parhomych.spring01.service;

import org.springframework.http.ResponseEntity;
import ru.parhomych.spring01.model.Teacher;

import java.util.List;

public interface TeacherService {

    ResponseEntity<Teacher> findTeacherById(int teacherId);

    ResponseEntity<List<Teacher>> findAllTeachers();

    ResponseEntity<Teacher> addNewTeacher(Teacher teacher);

    ResponseEntity<Teacher> editTeacher(Teacher teacher);

    Boolean removeTeacher(int teacherId);

}
