package ru.parhomych.spring01.service;

import ru.parhomych.spring01.model.Lesson;
import ru.parhomych.spring01.model.Student;

import java.util.List;

public interface LessonService {

    Lesson findLessonById(int lessonId);
    List<Lesson> findAllLessons();
    List<Lesson> findAllLessonsByStudent(int studentId);
    Lesson addNewLesson(Lesson lesson);
    Lesson editLesson(Lesson lesson);
    Boolean removeLesson(int lessonId);

}
