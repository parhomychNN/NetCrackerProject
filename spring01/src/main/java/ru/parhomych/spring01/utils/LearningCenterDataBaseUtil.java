package ru.parhomych.spring01.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class LearningCenterDataBaseUtil extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    /**
    * @return mapped relation entity_attribute_id <--> attr_name
    * There are different sets of entity_attribute_id, they depend on subject of entity
    * In my project they are: "Student", "Teacher", "Lesson", "Admin"
     * @param entity - subject of entity ("Student" || "Teacher" || "Lesson" || "Admin")
    * */
    public List<Map<String, Object>> getEntityAttrIdRelAttrNameByEntityName(String entity){
        String sql = "select ea.entity_attribute_id, a.attr_name\n" +
                "from entity_attribute ea left join attribute a on ea.attr_id = a.attr_id\n" +
                "where ea.ent_type_id in\n" +
                "      (\n" +
                "        select et.ent_type_id\n" +
                "        from entity_type et\n" +
                "        where et.entity like ? \n" +
                "        )\n" +
                ";";
        List<Map<String, Object>> entityAttrIdRelAttrName = getJdbcTemplate().queryForList(sql, entity);
        for (Map<String, Object> eaAttr : entityAttrIdRelAttrName){
            System.out.println(eaAttr);
        }
        return entityAttrIdRelAttrName;
    }

    /**
     * ID for adding new object
     * @return first available object ID
     */
    public int getTheNextObjectId(){
        int nextObjectId;

        String sql = "select max(obj_id) from object";
        nextObjectId = getJdbcTemplate().queryForObject(sql, Integer.class) + 1;
        System.out.println("LearningCenterDataBaseUtil.getTheNextObjectId =========> " + nextObjectId);
        return nextObjectId;
    }

    public void insertValueForObject(int objId, int entityAttributeId, Object value){

        String sql = "";
        if (value instanceof String){
            System.out.println("Inserting in val_text");
            sql = "INSERT INTO value (obj_id, entity_attribute_id, val_text) values (?, ?, ?)";
        } else if (value instanceof Integer){
            System.out.println("Inserting in val_int");
            sql = "INSERT INTO value (obj_id, entity_attribute_id, val_int) values (?, ?, ?)";
        } else if (value instanceof Double){
            System.out.println("Inserting in val_num");
            sql = "INSERT INTO value (obj_id, entity_attribute_id, val_numeric) values (?, ?, ?)";
        } else if (value instanceof Date){
            System.out.println("Inserting in val_date");
            sql = "INSERT INTO value (obj_id, entity_attribute_id, val_date) values (?, ?, ?)";
        }

        getJdbcTemplate().update(sql, objId, entityAttributeId, value);


    }

    /**
     * Adds new object in object's table
     * @param entityType can be "Admin", "Student", "Teacher", "Lesson"
     */
    public int insertNewObject(String entityType) {

        Map<String, Object> entIdMap;
        int entId;
        String sqlToAddObject;


        int objId = getTheNextObjectId();
        String sql = "";
        switch (entityType){
            case "Student":
                System.out.println("Adds student");

                sql = "select ent_type_id from entity_type where entity like 'Student'";
                entIdMap = getJdbcTemplate().queryForMap(sql);
                entId = Integer.valueOf(entIdMap.get("ent_type_id").toString());

                sqlToAddObject = "insert into object (obj_id, ent_type_id) values (?, ?)";
                getJdbcTemplate().update(sqlToAddObject, objId, entId);
                break;
            case "Admin":
                System.out.println("Adds admin");

                sql = "select ent_type_id from entity_type where entity like 'Admin'";
                entIdMap = getJdbcTemplate().queryForMap(sql);
                entId = Integer.valueOf(entIdMap.get("ent_type_id").toString());

                sqlToAddObject = "insert into object (obj_id, ent_type_id) values (?, ?)";
                getJdbcTemplate().update(sqlToAddObject, objId, entId);
                break;

            case "Teacher":
                System.out.println("Adds teacher");

                sql = "select ent_type_id from entity_type where entity like 'Teacher'";
                entIdMap = getJdbcTemplate().queryForMap(sql);
                entId = Integer.valueOf(entIdMap.get("ent_type_id").toString());

                sqlToAddObject = "insert into object (obj_id, ent_type_id) values (?, ?)";
                getJdbcTemplate().update(sqlToAddObject, objId, entId);
                break;
            case "Lesson":
                System.out.println("Adds lesson");

                sql = "select ent_type_id from entity_type where entity like 'Lesson'";
                entIdMap = getJdbcTemplate().queryForMap(sql);
                entId = Integer.valueOf(entIdMap.get("ent_type_id").toString());

                sqlToAddObject = "insert into object (obj_id, ent_type_id) values (?, ?)";
                getJdbcTemplate().update(sqlToAddObject, objId, entId);

                break;
        }
        System.out.println(objId);
        return objId;
    }
}
