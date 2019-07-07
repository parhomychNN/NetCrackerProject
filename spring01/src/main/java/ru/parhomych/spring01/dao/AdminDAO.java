package ru.parhomych.spring01.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.parhomych.spring01.model.Admin;

import java.util.List;

public interface AdminDAO {

    Admin getAdminById(int adminId);
    List<Admin> getAllAdmins();
    Admin addNewAdmin(Admin admin);

}
