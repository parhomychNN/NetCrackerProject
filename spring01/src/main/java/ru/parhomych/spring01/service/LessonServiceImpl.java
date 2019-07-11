package ru.parhomych.spring01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.parhomych.spring01.dao.LessonDAO;
import ru.parhomych.spring01.model.Lesson;
import ru.parhomych.spring01.model.Student;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonDAO lessonDAO;

    @Override
    public Lesson findLessonById(int lessonId) {
        return lessonDAO.getLessonById(lessonId);
    }

    @Override
    public List<Lesson> findAllLessons() {
        return lessonDAO.getAllLessons();
    }

    @Override
    public List<Lesson> findAllLessonsByStudent(int studentId) {
        return lessonDAO.getAllLessonsByStudent(studentId);
    }

    @Override
    public Lesson addNewLesson(Lesson lesson) {
        return lessonDAO.addNewLesson(lesson);
    }

    @Override
    public Lesson editLesson(Lesson lesson) {
        return lessonDAO.updateLesson(lesson);
    }

    @Override
    public Boolean removeLesson(int lessonId) {
        return lessonDAO.deleteLessonById(lessonId);
    }

}
