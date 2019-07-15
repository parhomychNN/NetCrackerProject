package ru.parhomych.spring01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.parhomych.spring01.dao.LessonDAO;
import ru.parhomych.spring01.model.Lesson;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    LessonDAO lessonDAO;

    @Override
    public ResponseEntity<Lesson> findLessonById(int lessonId) {
        Lesson lesson = lessonDAO.getLessonById(lessonId);
        if (lesson == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lesson, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<Lesson>> findAllLessons() {
        List<Lesson> lessons = lessonDAO.getAllLessons();
        if (lessons == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lessons, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<Lesson>> findAllLessonsByStudent(int studentId) {
        List<Lesson> lessons = lessonDAO.getAllLessonsByStudent(studentId);
        if (lessons == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lessons, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<Lesson>> findAllLessonsByTeacher(int teacherId) {
        List<Lesson> lessons = lessonDAO.getAllLessonsByTeacher(teacherId);
        if (lessons == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lessons, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Lesson> addNewLesson(Lesson lesson) {
        Lesson lessonToReturn = lessonDAO.addNewLesson(lesson);
        if (lessonToReturn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lessonToReturn, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Lesson> editLesson(Lesson lesson) {
        Lesson lessonToReturn = lessonDAO.updateLesson(lesson);
        if (lessonToReturn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lessonToReturn, HttpStatus.OK);
        }
    }

    @Override
    public Boolean removeLesson(int lessonId) {
        if (lessonDAO.getLessonById(lessonId) == null) {
            return false;
        } else {
            return lessonDAO.deleteLessonById(lessonId);
        }
    }
}
