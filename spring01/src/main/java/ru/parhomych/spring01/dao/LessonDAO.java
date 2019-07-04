package ru.parhomych.spring01.dao;

import ru.parhomych.spring01.model.Lesson;
import ru.parhomych.spring01.model.Student;

import java.util.List;

public interface LessonDAO {

    Lesson getLessonById(int lessonId);
    List<Lesson> getAllLessons();
    Lesson addNewLesson(Lesson lesson);

    List<Lesson> getAllLessonsByStudent(int studentId);
}
