package ru.parhomych.spring01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.parhomych.spring01.model.InfoMessage;
import ru.parhomych.spring01.model.Lesson;
import ru.parhomych.spring01.model.Student;
import ru.parhomych.spring01.model.Teacher;
import ru.parhomych.spring01.service.LessonService;
import ru.parhomych.spring01.service.StudentService;
import ru.parhomych.spring01.service.TeacherService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class LessonController {

    @Autowired
    LessonService lessonService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable String lessonId){

        int lessonIdInt = Integer.valueOf(lessonId);
        Lesson lesson = lessonService.findLessonById(lessonIdInt);
        if (lesson == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lesson, HttpStatus.OK);
        }

    }

    @GetMapping("/lessons")
    public ResponseEntity<List<Lesson>> getAllLessons(){

        ArrayList<Lesson> lessons = (ArrayList<Lesson>) lessonService.findAllLessons();
        if (lessons == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lessons, HttpStatus.OK);
        }

    }

    @GetMapping("/lessons/student/{studId}")
    public ResponseEntity<List<Lesson>> getAllLessonsByStudentId(@PathVariable String studId){

        //Student student = studentService.findStudentById(Integer.valueOf(studId));
        ArrayList<Lesson> lessons = (ArrayList<Lesson>) lessonService.findAllLessonsByStudent(Integer.valueOf(studId));
        if (lessons == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lessons, HttpStatus.OK);
        }

    }

    @PostMapping("/lessons/add")
    public ResponseEntity<InfoMessage> addNewLesson(){

        Lesson lesson = new Lesson();
        Student student = studentService.findStudentById(1);
        Teacher teacher = teacherService.findTeacherById(5);
        lesson.setSubject("Русский 2 класс");
        lesson.setDate(new Date(11,11,1));
        lesson.setPrice(1255.0);
        lesson.setTeacher(teacher);
        lesson.setStudent(student);

        Lesson addedLesson = lessonService.addNewLesson(lesson);

        return new ResponseEntity<>(new InfoMessage("Lesson " + addedLesson.getId() + " added"), HttpStatus.OK);

    }

}
