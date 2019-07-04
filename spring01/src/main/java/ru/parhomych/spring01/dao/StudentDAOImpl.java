package ru.parhomych.spring01.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ru.parhomych.spring01.model.Student;
import ru.parhomych.spring01.utils.LearningCenterDataBaseUtil;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDAOImpl extends JdbcDaoSupport implements StudentDAO {


    @Autowired DataSource dataSource;
    @Autowired LearningCenterDataBaseUtil learningCenterDataBaseUtil;

    final String sqlGetStudentById = "select * from value\n" +
            "where entity_attribute_id in\n" +
            "    (select entity_attribute.entity_attribute_id from entity_attribute where ent_type_id in\n" +
            "        (select entity_type.ent_type_id from entity_type where entity like 'Student')\n" +
            "    )\n" +
            "and obj_id = ?;\n";

    final String sqlGetAllStudentsIds = "select o.obj_id id\n" +
            "from object o, entity_type et\n" +
            "where o.ent_type_id = et.ent_type_id and et.entity like 'Student'";


    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Student getStudentById(int studentId) {

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sqlGetStudentById, studentId);

        Student resultStudent;
        if (rows.size() != 0){
            resultStudent = new Student();
        } else {
            resultStudent = null;
        }

        for (Map<String, Object> rowStudentAttribute : rows) {
            switch ((int)rowStudentAttribute.get("entity_attribute_id")){
                case 1: // id
                    resultStudent.setStudentId((int)rowStudentAttribute.get("val_int"));
                    break;
                case 2: // firstname
                    resultStudent.setFirstName((String) rowStudentAttribute.get("val_text"));
                    break;
                case 3: // lastname
                    resultStudent.setLastName((String) rowStudentAttribute.get("val_text"));
                    break;
                case 4: // birthday
                    resultStudent.setDate((Date) rowStudentAttribute.get("val_date"));
            }
        }
        System.out.println("StudentDAOImpl.findStudentById " + resultStudent);
        return resultStudent;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Student> getAllStudents() {
        // calculate ID's of all the students

        List<Map<String, Object>> studentsIds = getJdbcTemplate().queryForList(sqlGetAllStudentsIds);

        if (studentsIds.size() == 0){
            return null;
        }
        // find list of lessons by ID's
        List<Student> resultStudents = new ArrayList<>();
        for(Map<String, Object> studentId : studentsIds){
            Student student = getStudentById((int)studentId.get("id"));
            resultStudents.add(student);
        }

        return resultStudents;
    }

    @Override
    public Student addNewStudent(Student student) {
       List<Map<String, Object>> eaattrList = learningCenterDataBaseUtil.getEntityAttrIdRelAttrNameByEntityName("Student");

        int objId = learningCenterDataBaseUtil.insertNewObject("Student");
        System.out.println("StudentDAOImpl.addNewTeacher objId ==========> " + objId);

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
                            student.getFirstName()
                    );
                    break;
                case "last_name":
                    learningCenterDataBaseUtil.insertValueForObject(
                            objId,
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            student.getLastName()
                    );
                    break;
                case "birth_date":
                    learningCenterDataBaseUtil.insertValueForObject(
                            objId,
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            student.getDate()
                    );
                    break;
            }
        }

        return getStudentById(objId);

    }
}
