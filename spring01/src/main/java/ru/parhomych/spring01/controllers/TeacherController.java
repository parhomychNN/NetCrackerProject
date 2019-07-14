package ru.parhomych.spring01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.parhomych.spring01.model.Teacher;
import ru.parhomych.spring01.service.TeacherService;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String teacherId) {

        return teacherService.findTeacherById(Integer.valueOf(teacherId));

    }

    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {

        return teacherService.findAllTeachers();

    }

    @Produces(MediaType.APPLICATION_JSON)
    @PostMapping("/teachers/add")
    public ResponseEntity<Teacher> addNewTeacher(@RequestBody Teacher teacherJSON) {

        return teacherService.addNewTeacher(teacherJSON);

    }

    @Produces(MediaType.APPLICATION_JSON)
    @PutMapping("/teacher/")
    public ResponseEntity<Teacher> editTeacher(@RequestBody Teacher teacherJSON) {

        return teacherService.editTeacher(teacherJSON);

    }

    @Produces(MediaType.APPLICATION_JSON)
    @DeleteMapping("/teacher/{teacherId}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String teacherId) {

        Boolean statusOfDeleting = teacherService.removeTeacher(Integer.valueOf(teacherId));
        return new ResponseEntity<String>(
                statusOfDeleting.toString(),
                HttpStatus.OK
        );

    }

}
