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
    public ResponseEntity<Admin> getAdminById(@PathVariable String adminId){

        int adminIdInt = Integer.valueOf(adminId);
        Admin admin = adminService.findAdminById(adminIdInt);
        if (admin == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        }

    }

    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAllAdmins(){

        List<Admin> admins = adminService.findAllAdmins();
        if (admins == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(admins, HttpStatus.OK);
        }

    }

    @PostMapping("/admins/add")
    public ResponseEntity<InfoMessage> addNewAdmin(@RequestBody Admin adminJSON){

        Admin addedAdmin = adminService.addNewAdmin(adminJSON);
        return new ResponseEntity<> (
                new InfoMessage("Admin " + addedAdmin.getId() + " added"),
                HttpStatus.OK
        );

    }

    @Produces(MediaType.APPLICATION_JSON)
    @PutMapping("/admin/")
    public ResponseEntity<Admin> editStudent(@RequestBody Admin adminJSON) {

        System.out.println("AdminController.editStudent" + adminJSON);
        return new ResponseEntity<Admin> (
                adminService.editAdmin(adminJSON),
                HttpStatus.OK
        );

    }

    @Produces(MediaType.APPLICATION_JSON)
    @DeleteMapping("/admin/{adminId}")
    public ResponseEntity<String> deleteStudent (@PathVariable String adminId) {

        int adminIdInt = Integer.valueOf(adminId);
        Boolean statusOfDeleting = adminService.removeAdmin(adminIdInt);
        return new ResponseEntity<String>(
                statusOfDeleting.toString(),
                HttpStatus.OK
        );

    }


}
