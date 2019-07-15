package ru.parhomych.spring01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.parhomych.spring01.dao.AdminDAO;
import ru.parhomych.spring01.model.Admin;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDAO adminDAO;

    @Override
    public ResponseEntity<Admin> findAdminById(int adminId) {
        Admin admin = adminDAO.getAdminById(adminId);
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<Admin>> findAllAdmins() {
        List<Admin> admins = adminDAO.getAllAdmins();
        if (admins == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(admins, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Admin> addNewAdmin(Admin admin) {
        Admin adminToReturn = adminDAO.addNewAdmin(admin);
        if (adminToReturn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(adminToReturn, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Admin> editAdmin(Admin admin) {
        Admin adminToReturn = adminDAO.updateAdmin(admin);
        if (adminToReturn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(adminToReturn, HttpStatus.OK);
        }
    }

    @Override
    public Boolean removeAdmin(int adminId) {
        if (adminDAO.getAdminById(adminId) == null) {
            return false;
        } else {
            return adminDAO.deleteAdminById(adminId);
        }
    }
}
