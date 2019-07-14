package ru.parhomych.spring01.dao;

import ru.parhomych.spring01.model.Lesson;
import ru.parhomych.spring01.model.Student;

import java.util.List;

public interface LessonDAO {

    Lesson addNewLesson(Lesson lesson);

    Lesson getLessonById(int lessonId);

    List<Lesson> getAllLessons();

    List<Lesson> getAllLessonsByStudent(int studentId);

    List<Lesson> getAllLessonsByTeacher(int teacherId);

    Lesson updateLesson(Lesson lesson);

    Boolean deleteLessonById(int lessonId);

}
