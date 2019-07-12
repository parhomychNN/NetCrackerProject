package ru.parhomych.spring01.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ru.parhomych.spring01.model.Admin;
import ru.parhomych.spring01.utils.LearningCenterDataBaseUtil;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AdminDAOImpl extends JdbcDaoSupport implements AdminDAO {

    @Autowired
    DataSource dataSource;
    @Autowired
    LearningCenterDataBaseUtil learningCenterDataBaseUtil;


    final String sqlGetAdminById = "select * from value\n" +
            "where entity_attribute_id in\n" +
            "    (select entity_attribute.entity_attribute_id from entity_attribute where ent_type_id in\n" +
            "        (select entity_type.ent_type_id from entity_type where entity like 'Admin')\n" +
            "    )\n" +
            "and obj_id = ?;\n";

    final String sqlGetAllAdminsIds = "select o.obj_id id\n" +
            "from object o, entity_type et\n" +
            "where o.ent_type_id = et.ent_type_id and et.entity like 'Admin'";

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Admin getAdminById(int adminId) {
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sqlGetAdminById, adminId);

        Admin resultAdmin;
        if (rows.size() != 0){
            resultAdmin = new Admin();
        } else {
            resultAdmin = null;
        }

        for (Map<String, Object> rowAdminAttribute : rows) {
            switch ((int)rowAdminAttribute.get("entity_attribute_id")){
                case 13: // id
                    resultAdmin.setId((int)rowAdminAttribute.get("val_int"));
                    break;
                case 14: // firstname
                    resultAdmin.setFirstName((String) rowAdminAttribute.get("val_text"));
                    break;
                case 15: // lastname
                    resultAdmin.setLastName((String) rowAdminAttribute.get("val_text"));
                    break;
                case 16: // position
                    resultAdmin.setPosition((String) rowAdminAttribute.get("val_text"));
            }
        }
        System.out.println("AdminDAOImpl.findStudentById " + resultAdmin);
        return resultAdmin;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Admin> getAllAdmins() {
        // calculate ID's of all the admins

        List<Map<String, Object>> adminsIds = getJdbcTemplate().queryForList(sqlGetAllAdminsIds);

        if (adminsIds.size() == 0){
            return null;
        }
        // find list of admins by ID's
        List<Admin> resultAdmins = new ArrayList<>();
        for(Map<String, Object> adminId : adminsIds){
            Admin admin = getAdminById((int)adminId.get("id"));
            resultAdmins.add(admin);
        }

        return resultAdmins;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Admin addNewAdmin(Admin admin) {
        List<Map<String, Object>> eaattrList = learningCenterDataBaseUtil.getEntityAttrIdRelAttrNameByEntityName("Admin");

        int objId = learningCenterDataBaseUtil.insertNewObject("Admin");
        System.out.println("AdminDAOImpl.addNewAdmin objId ==========> " + objId);

        for(Map<String, Object> eaattr : eaattrList){
            switch (eaattr.get("attr_name").toString()){ // entity_attribute_id (какую цифру ставить в поле)
                case "id":
                    learningCenterDataBaseUtil.insertValueForObject(
                            objId,
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            objId
                    );
                    break;
                case "first_name":
                    learningCenterDataBaseUtil.insertValueForObject(
                            objId,
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            admin.getFirstName()
                    );
                    break;
                case "last_name":
                    learningCenterDataBaseUtil.insertValueForObject(
                            objId,
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            admin.getLastName()
                    );
                    break;
                case "position":
                    learningCenterDataBaseUtil.insertValueForObject(
                            objId,
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            admin.getPosition()
                    );
                    break;
            }
        }

        return getAdminById(objId);
    }

    @Override
    public Admin updateAdmin(Admin admin) {

        List<Map<String, Object>> eaattrList =
                learningCenterDataBaseUtil.getEntityAttrIdRelAttrNameByEntityName("Admin");

        for(Map<String, Object> eaattr : eaattrList){
            // entity_attribute_id (какую цифру ставить в поле)
            switch (eaattr.get("attr_name").toString()){
                case "first_name":
                    learningCenterDataBaseUtil.updateValueForObject(
                            admin.getId(),
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            admin.getFirstName()
                    );
                    break;
                case "last_name":
                    learningCenterDataBaseUtil.updateValueForObject(
                            admin.getId(),
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            admin.getLastName()
                    );
                    break;
                case "position":
                    learningCenterDataBaseUtil.updateValueForObject(
                            admin.getId(),
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            admin.getPosition()
                    );
                    break;
            }
        }
        return getAdminById(admin.getId());

    }

    @Override
    public Boolean deleteAdminById(int adminId) {
        List<Map<String, Object>> eaattrList =
                learningCenterDataBaseUtil.getEntityAttrIdRelAttrNameByEntityName("Admin");
        // removing rows from value table
        for (Map<String, Object> eaAttr : eaattrList){
            learningCenterDataBaseUtil.deleteRowInValue(
                    adminId,
                    Integer.valueOf(eaAttr.get("entity_attribute_id").toString())
            );
        }
        // removing row from object table
        learningCenterDataBaseUtil.removeRowFromObject(adminId);
        return true;
    }
}
