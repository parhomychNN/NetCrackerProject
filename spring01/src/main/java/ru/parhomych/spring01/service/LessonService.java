package ru.parhomych.spring01.service;

import org.springframework.http.ResponseEntity;
import ru.parhomych.spring01.model.Lesson;
import ru.parhomych.spring01.model.Student;

import java.util.List;

public interface LessonService {

    ResponseEntity<Lesson> findLessonById(int lessonId);

    ResponseEntity<List<Lesson>> findAllLessons();

    ResponseEntity<List<Lesson>> findAllLessonsByStudent(int studentId);

    ResponseEntity<List<Lesson>> findAllLessonsByTeacher(int teacherId);

    ResponseEntity<Lesson> addNewLesson(Lesson lesson);

    ResponseEntity<Lesson> editLesson(Lesson lesson);

    Boolean removeLesson(int lessonId);

}
