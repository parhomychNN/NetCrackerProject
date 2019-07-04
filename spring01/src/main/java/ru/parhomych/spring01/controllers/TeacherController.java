package ru.parhomych.spring01.controllers;

import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.parhomych.spring01.model.InfoMessage;
import ru.parhomych.spring01.model.Student;
import ru.parhomych.spring01.model.Teacher;
import ru.parhomych.spring01.service.StudentService;
import ru.parhomych.spring01.service.TeacherService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String teacherId){

        int teacherIdInt = Integer.valueOf(teacherId);
        Teacher teacher = teacherService.findTeacherById(teacherIdInt);
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        }

    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers(){

        List<Teacher> teachers = teacherService.findAllTeachers();
        if (teachers == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(teachers, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/teachers/add")
    public ResponseEntity<InfoMessage> addNewTeacher(@RequestBody Teacher teacherJSON){

        Teacher addedTeacher = teacherService.addNewTeacher(teacherJSON);

        return new ResponseEntity<>(
                new InfoMessage("Teacher " + addedTeacher.getTeacherId() + " added"),
                HttpStatus.OK
        );

    }

}
