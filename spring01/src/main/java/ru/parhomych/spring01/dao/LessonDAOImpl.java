package ru.parhomych.spring01.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.parhomych.spring01.model.Lesson;
import ru.parhomych.spring01.model.Student;
import ru.parhomych.spring01.model.Teacher;
import ru.parhomych.spring01.service.StudentService;
import ru.parhomych.spring01.service.TeacherService;
import ru.parhomych.spring01.utils.LearningCenterDataBaseUtil;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.*;

@Repository
public class LessonDAOImpl extends JdbcDaoSupport implements LessonDAO {

    @Autowired
    DataSource dataSource;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    LearningCenterDataBaseUtil learningCenterDataBaseUtil;

    final String sqlGetLessonByIdLessonTable = "SELECT * FROM lesson WHERE lesson_id = ?";

    final String sqlGetLessonByIdValueTable = "select * from value\n" +
            "where entity_attribute_id in\n" +
            "    (select entity_attribute.entity_attribute_id from entity_attribute where ent_type_id in\n" +
            "        (select entity_type.ent_type_id from entity_type where entity like 'Lesson')\n" +
            "    )\n" +
            "and obj_id = ?;\n";

    final String sqlGetAllLessonsIds = "select o.obj_id id\n" +
            "from object o, entity_type et\n" +
            "where o.ent_type_id = et.ent_type_id and et.entity like 'Lesson'";

    final String sqlGetAllLessonsIdsByStudentId = "select lesson_id from lesson where student_id = ?";


    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Lesson getLessonById(int lessonId) {

        // Lesson table
        SqlRowSet resultSet = getJdbcTemplate().queryForRowSet(sqlGetLessonByIdLessonTable, lessonId);
        if (resultSet.next() == false) {
            return null;
        } else {
            Lesson resultLesson = new Lesson();
            resultLesson.setId(resultSet.getInt("lesson_id"));
            Teacher teacher = teacherService.findTeacherById(resultSet.getInt("teacher_id"));
            resultLesson.setTeacher(teacher);
            Student student = studentService.findStudentById(resultSet.getInt("student_id"));
            resultLesson.setStudent(student);

            // Value table
            List<Map<String, Object>> rowsAttributes = getJdbcTemplate().queryForList(sqlGetLessonByIdValueTable, lessonId);

            for (Map<String, Object> rowLessonAttribute : rowsAttributes) {
                switch ((int) rowLessonAttribute.get("entity_attribute_id")) {
                    case 10: // price
                        BigDecimal priceBigDecimal = (BigDecimal) rowLessonAttribute.get("val_numeric");
                        resultLesson.setPrice(priceBigDecimal.doubleValue());
                        break;
                    case 11: // lessondate
                        resultLesson.setDate((Date) rowLessonAttribute.get("val_date"));
                        break;
                    case 12: // subject
                        resultLesson.setSubject((String) rowLessonAttribute.get("val_text"));
                        break;
                }
            }
            System.out.println("StudentDAOImpl.findStudentById " + resultLesson);

            return resultLesson;
        }

}

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Lesson> getAllLessons() {
        // calculate ID's of all the lessons
        List<Map<String, Object>> lessonsIds = getJdbcTemplate().queryForList(sqlGetAllLessonsIds);

        if (lessonsIds.size() == 0) {
            return null;
        }

        // find list of lessons by ID's
        List<Lesson> resultLessons = new ArrayList<Lesson>();
        for (Map<String, Object> lessonId : lessonsIds) {
            Lesson lesson = getLessonById((int) lessonId.get("id"));
            resultLessons.add(lesson);
        }

        return resultLessons;
    }

    @Override
    public Lesson addNewLesson(Lesson lesson) {

        System.out.println(lesson);
        List<Map<String, Object>> eaattrList =
                learningCenterDataBaseUtil.getEntityAttrIdRelAttrNameByEntityName("Lesson");

        // Table OBJECT
        int objId = learningCenterDataBaseUtil.insertNewObject("Lesson");

        // Table VALUES
        for (Map<String, Object> eaattr : eaattrList) {
            switch (eaattr.get("attr_name").toString()) { // entity_attribute_id (какую цифру ставить в поле)
                case "id":
                    learningCenterDataBaseUtil.insertValueForObject(
                            objId,
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            objId
                    );
                    break;
                case "price":
                    learningCenterDataBaseUtil.insertValueForObject(
                            objId,
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            lesson.getPrice()
                    );
                    break;
                case "lesson_date":
                    learningCenterDataBaseUtil.insertValueForObject(
                            objId,
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            lesson.getDate()
                    );
                    break;
                case "subject":
                    learningCenterDataBaseUtil.insertValueForObject(
                            objId,
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            lesson.getSubject()
                    );
                    break;
            }
        }

        // Table LESSON
        String sqlForLessonsTable = "insert into lesson (lesson_id, teacher_id, student_id) values (?, ?, ?)";
        getJdbcTemplate().update(
                sqlForLessonsTable,
                objId,
                lesson.getTeacher().getTeacherId(),
                lesson.getStudent().getStudentId()
        );

        return getLessonById(objId);

    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Lesson> getAllLessonsByStudent(int studentId) {
        // calculate ID's of all the lessons
        List<Map<String, Object>> lessonsIds = getJdbcTemplate().queryForList(sqlGetAllLessonsIdsByStudentId, studentId);

        System.out.println("lessonsIds = [" + lessonsIds + "]");

        if (lessonsIds.size() == 0) {
            return null;
        }

        // find list of lessons by ID's
        List<Lesson> resultLessons = new ArrayList<Lesson>();
        for (Map<String, Object> lessonId : lessonsIds) {
            Lesson lesson = getLessonById((int) lessonId.get("lesson_id"));
            resultLessons.add(lesson);
        }

        return resultLessons;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Lesson updateLesson(Lesson lesson) {

        // Table VALUE
        List<Map<String, Object>> eaattrList =
                learningCenterDataBaseUtil.getEntityAttrIdRelAttrNameByEntityName("Lesson");

        for(Map<String, Object> eaattr : eaattrList){
            // entity_attribute_id (какую цифру ставить в поле)
            switch (eaattr.get("attr_name").toString()){
                case "price":
                    learningCenterDataBaseUtil.updateValueForObject(
                            lesson.getId(),
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            lesson.getPrice()
                    );
                    break;
                case "lesson_date":
                    learningCenterDataBaseUtil.updateValueForObject(
                            lesson.getId(),
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            lesson.getDate()
                    );
                    break;
                case "subject":
                    learningCenterDataBaseUtil.updateValueForObject(
                            lesson.getId(),
                            Integer.valueOf(eaattr.get("entity_attribute_id").toString()),
                            lesson.getSubject()
                    );
                    break;
            }
        }

        // Table LESSON
        learningCenterDataBaseUtil.updateLessonInfoInLessonTable(
                lesson.getId(),
                lesson.getStudent().getStudentId(),
                lesson.getTeacher().getTeacherId()
        );

        return getLessonById(lesson.getId());

    }

    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Boolean deleteLessonById(int lessonId) {

        // Table VALUES
        List<Map<String, Object>> eaattrList =
                learningCenterDataBaseUtil.getEntityAttrIdRelAttrNameByEntityName("Lesson");
        for (Map<String, Object> eaAttr : eaattrList){
            learningCenterDataBaseUtil.deleteRowInValue(
                    lessonId,
                    Integer.valueOf(eaAttr.get("entity_attribute_id").toString())
            );
        }
        // Table OBJECT
        learningCenterDataBaseUtil.removeRowFromObject(lessonId);
        // Table LESSON
        learningCenterDataBaseUtil.removeRowFromLesson(lessonId);

        return true;

    }
}
