package ru.parhomych.spring01.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.parhomych.spring01.model.Admin;

import java.util.List;

public interface AdminService {

    ResponseEntity<Admin> findAdminById(int adminId);

    ResponseEntity<List<Admin>> findAllAdmins();

    ResponseEntity<Admin> addNewAdmin(Admin admin);

    ResponseEntity<Admin> editAdmin(Admin admin);

    Boolean removeAdmin(int adminId);

}
