package ru.parhomych.spring01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.parhomych.spring01.model.Lesson;
import ru.parhomych.spring01.service.LessonService;
import ru.parhomych.spring01.service.StudentService;
import ru.parhomych.spring01.service.TeacherService;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    public ResponseEntity<Lesson> getLessonById(@PathVariable String lessonId) {
        return lessonService.findLessonById(Integer.valueOf(lessonId));
    }

    @GetMapping("/lessons")
    public ResponseEntity<List<Lesson>> getAllLessons() {
        return lessonService.findAllLessons();
    }

    @GetMapping("/lessons/student/{studId}")
    public ResponseEntity<List<Lesson>> getAllLessonsByStudentId(@PathVariable String studId) {
        return lessonService.findAllLessonsByStudent(Integer.valueOf(studId));
    }

    @GetMapping("/lessons/teacher/{teachId}")
    public ResponseEntity<List<Lesson>> getAllLessonsByTeacherId(@PathVariable String teachId) {
        return lessonService.findAllLessonsByTeacher(Integer.valueOf(teachId));
    }

    @PostMapping("/lessons/add")
    public ResponseEntity<Lesson> addNewLesson(@RequestBody Lesson lessonJSON) {
        return lessonService.addNewLesson(lessonJSON);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @PutMapping("/lesson/")
    public ResponseEntity<Lesson> editLesson(@RequestBody Lesson lessonJSON) {
        return lessonService.editLesson(lessonJSON);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @DeleteMapping("/lesson/{lessonId}")
    public ResponseEntity<String> deleteLesson(@PathVariable String lessonId) {
        Boolean statusOfDeleting = lessonService.removeLesson(Integer.valueOf(lessonId));
        return new ResponseEntity<String>(
                statusOfDeleting.toString(),
                HttpStatus.OK
        );
    }
}
