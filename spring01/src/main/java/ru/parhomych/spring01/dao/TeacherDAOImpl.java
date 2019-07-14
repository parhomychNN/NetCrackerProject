package ru.parhomych.spring01.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ru.parhomych.spring01.model.Lesson;
import ru.parhomych.spring01.model.Teacher;
import ru.parhomych.spring01.service.LessonService;
import ru.parhomych.spring01.utils.LearningCenterDataBaseUtil;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TeacherDAOImpl extends JdbcDaoSupport implements TeacherDAO {

    @Autowired
    DataSource dataSource;
    @Autowired
    LearningCenterDataBaseUtil learningCenterDataBaseUtil;
    @Autowired
    LessonService lessonService;

    final String sqlGetTeacherById = "select * from value\n" +
            "where entity_attribute_id in\n" +
            "    (select entity_attribute.entity_attribute_id from entity_attribute where ent_type_id in\n" +
            "        (select entity_type.ent_type_id from entity_type where entity like 'Teacher')\n" +
            "    )\n" +
            "and obj_id = ?;\n";
    final String sqlGetAllTeachersIds = "select o.obj_id id\n" +
            "from object o, entity_type et\n" +
            "where o.ent_type_id = et.ent_type_id and et.entity like 'Teacher'";

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Teacher getTeacherById(int teacherId) {

        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sqlGetTeacherById, teacherId);
        Teacher resultTeacher;
        if (rows.size() != 0) {
            resultTeacher = new Teacher();
        } else {
            resultTeacher = null;
        }
        for (Map<String, Object> rowTeacherAttribute : rows) {
            switch ((int) rowTeacherAttribute.get("entity_attribute_id")) {
                case 5: // id
                    resultTeacher.setTeacherId((int) rowTeacherAttribute.get("val_int"));
                    break;
                case 6: // firstname
                    resultTeacher.setFirstName((String) rowTeacherAttribute.get("val_text"));
                    break;
                case 7: // lastname
                    resultTeacher.setLastName((String) rowTeacherAttribute.get("val_text"));
                    break;
                case 8: // science degree
                    resultTeacher.setAcademicDegree((String) rowTeacherAttribute.get("val_text"));
            }
        }
        System.out.println("TeacherDAOImpl.findTeacherById " + resultTeacher);
        return resultTeacher;

    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Teacher> getAllTeachers() {

        // calculate ID's of all the teachers
        List<Map<String, Object>> teachersIds = getJdbcTemplate().queryForList(sqlGetAllTeachersIds);
        if (teachersIds.size() == 0) {
            return null;
        }
        // find list of lessons by ID's
        List<Teacher> resultTeachers = new ArrayList<>();
        for (Map<String, Object> teacherId : teachersIds) {
            resultTeachers.add(getTeacherById((int) teacherId.get("id")));
        }
        return resultTeachers;

    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Teacher addNewTeacher(Teacher teacher) {

        List<Map<String, Object>> eaattrList =
                learningCenterDataBaseUtil.getEntityAttrIdRelAttrNameByEntityName("Teacher");
        int objId = learningCenterDataBaseUtil.insertNewObject("Teacher");
        for (Map<String, Object> eaattr : eaattrList) {
            switch (eaattr.get("attr_name").toString()) { // entity_attribute_id (какую цифру ставить в поле)
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
                            teacher.getFirstName()
                    );
                    break;
                case "last_name":
                    learningCenterDataBaseUtil.insertValueForObject(
                            objId,
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            teacher.getLastName()
                    );
                    break;
                case "academic_degree":
                    learningCenterDataBaseUtil.insertValueForObject(
                            objId,
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            teacher.getAcademicDegree()
                    );
                    break;
            }
        }
        return getTeacherById(objId);

    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Teacher updateTeacher(Teacher teacher) {

        List<Map<String, Object>> eaattrList =
                learningCenterDataBaseUtil.getEntityAttrIdRelAttrNameByEntityName("Teacher");
        for (Map<String, Object> eaattr : eaattrList) {
            // entity_attribute_id (какую цифру ставить в поле)
            switch (eaattr.get("attr_name").toString()) {
                case "first_name":
                    learningCenterDataBaseUtil.updateValueForObject(
                            teacher.getTeacherId(),
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            teacher.getFirstName()
                    );
                    break;
                case "last_name":
                    learningCenterDataBaseUtil.updateValueForObject(
                            teacher.getTeacherId(),
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            teacher.getLastName()
                    );
                    break;
                case "academic_degree":
                    learningCenterDataBaseUtil.updateValueForObject(
                            teacher.getTeacherId(),
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            teacher.getAcademicDegree()
                    );
                    break;
            }
        }
        return getTeacherById(teacher.getTeacherId());

    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Boolean deleteTeacherById(int teacherId) {

        // delete all attached lessons
        List<Lesson> lessonsAttachedToThisTeacher = lessonService.findAllLessonsByTeacher(teacherId).getBody();
        for (Lesson lesson : lessonsAttachedToThisTeacher) {
            lessonService.removeLesson(lesson.getId());
        }
        List<Map<String, Object>> eaattrList =
                learningCenterDataBaseUtil.getEntityAttrIdRelAttrNameByEntityName("Teacher");
        // removing rows from value table
        for (Map<String, Object> eaAttr : eaattrList) {
            learningCenterDataBaseUtil.deleteRowInValue(
                    teacherId,
                    Integer.valueOf(eaAttr.get("entity_attribute_id").toString())
            );
        }
        // removing row from object table
        learningCenterDataBaseUtil.removeRowFromObject(teacherId);
        return true;

    }

}
