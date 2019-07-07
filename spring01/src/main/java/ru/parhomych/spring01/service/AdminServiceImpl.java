package ru.parhomych.spring01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.parhomych.spring01.dao.AdminDAO;
import ru.parhomych.spring01.model.Admin;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDAO adminDAO;

    @Override
    public Admin findAdminById(int adminId) {
        return adminDAO.getAdminById(adminId);
    }

    @Override
    public List<Admin> findAllAdmins() {
        return adminDAO.getAllAdmins();
    }

    @Override
    public Admin addNewAdmin(Admin admin) {
        return adminDAO.addNewAdmin(admin);
    }
}
