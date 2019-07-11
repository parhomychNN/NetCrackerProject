package ru.parhomych.spring01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.parhomych.spring01.model.InfoMessage;
import ru.parhomych.spring01.model.Lesson;
import ru.parhomych.spring01.model.Student;
import ru.parhomych.spring01.model.Teacher;
import ru.parhomych.spring01.service.LessonService;
import ru.parhomych.spring01.service.StudentService;
import ru.parhomych.spring01.service.TeacherService;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    public ResponseEntity<Lesson> addNewLesson(@RequestBody Lesson lessonJSON){

        Lesson addedLesson = lessonService.addNewLesson(lessonJSON);
        return new ResponseEntity<>(addedLesson, HttpStatus.OK);

    }

    @Produces(MediaType.APPLICATION_JSON)
    @PutMapping("/lesson/")
    public ResponseEntity<Lesson> editLesson(@RequestBody Lesson lessonJSON) {

        return new ResponseEntity<Lesson> (
                lessonService.editLesson(lessonJSON),
                HttpStatus.OK
        );

    }

    @Produces(MediaType.APPLICATION_JSON)
    @DeleteMapping("/lesson/{lessonId}")
    public ResponseEntity<String> deleteLesson (@PathVariable String lessonId) {

        int lessonIdInt = Integer.valueOf(lessonId);
        Boolean statusOfDeleting = lessonService.removeLesson(lessonIdInt);
        return new ResponseEntity<String>(
                statusOfDeleting.toString(),
                HttpStatus.OK
        );

    }

}
