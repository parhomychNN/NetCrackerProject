package ru.parhomych.spring01.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.parhomych.spring01.model.Admin;
import ru.parhomych.spring01.model.InfoMessage;
import ru.parhomych.spring01.service.AdminService;
import ru.parhomych.spring01.utils.LearningCenterDataBaseUtil;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable String adminId) {

        return adminService.findAdminById(Integer.valueOf(adminId));

    }

    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAllAdmins() {

        return adminService.findAllAdmins();

    }

    @PostMapping("/admins/add")
    public ResponseEntity<Admin> addNewAdmin(@RequestBody Admin adminJSON) {

        return adminService.addNewAdmin(adminJSON);

    }

    @Produces(MediaType.APPLICATION_JSON)
    @PutMapping("/admin/")
    public ResponseEntity<Admin> editAdmin(@RequestBody Admin adminJSON) {

        return adminService.editAdmin(adminJSON);

    }

    @Produces(MediaType.APPLICATION_JSON)
    @DeleteMapping("/admin/{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable String adminId) {

        Boolean statusOfDeleting = adminService.removeAdmin(Integer.valueOf(adminId));
        return new ResponseEntity<String>(
                statusOfDeleting.toString(),
                HttpStatus.OK
        );

    }

}
