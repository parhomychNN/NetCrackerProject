package ru.parhomych.spring01.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.parhomych.spring01.model.Admin;

import java.util.List;

public interface AdminService {

    Admin findAdminById(int adminId);
    List<Admin> findAllAdmins();
    Admin addNewAdmin(Admin admin);

}
