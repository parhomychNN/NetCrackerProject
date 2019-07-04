-- *********************** Query tests ************************

-- *** Text representation of entity_attribute table ***
select ea.entity_attribute_id, et.entity, a.attr_name
from entity_type et, attribute a, entity_attribute ea
where et.ent_type_id = ea.ent_type_id and ea.attr_id = a.attr_id;

-- *** Text representation of concrete object's entity table ***
select o.obj_id, e.entity
from object o, entity_type e
where o.ent_type_id = e.ent_type_id;

-- *** Text representation of lesson ***
select l.lesson_id, vstud.val_text student, vteach.val_text teacher,vles.val_numeric price,vles.val_date les_date,vles.val_text as name
from lesson l, value vles, value vteach, value vstud
where l.lesson_id = vles.obj_id
  and vstud.obj_id = l.student_id
  and vteach.obj_id = l.teacher_id;
;

select *
from (lesson l left join value vles on l.lesson_id = vles.obj_id)
       left join value vteach on l.teacher_id = vteach.obj_id;

select * from value
where entity_attribute_id in
      (select entity_attribute.entity_attribute_id from entity_attribute where ent_type_id in
                                                                               (select entity_type.ent_type_id from entity_type where entity like 'Student')
      )
  and obj_id = 2;
;

select * from value
where entity_attribute_id in
      (select entity_attribute.entity_attribute_id from entity_attribute where ent_type_id in
                                                                               (select entity_type.ent_type_id from entity_type where entity like 'Teacher')
      )
  and obj_id = 4;
;


-- **************** ent_attr join attr ******************
select ea.entity_attribute_id, a.attr_name
from entity_attribute ea left join attribute a on ea.attr_id = a.attr_id
where ea.ent_type_id in
      (
        select et.ent_type_id
        from entity_type et
        where et.entity like 'Teacher'
        )
;

-- **************** get the next free id for objects *****************
select max(obj_id) from object;

/*
select entity_attribute_id
from entity_attribute
where ent_type_id in (select ent_type_id from entity_type where entity like 'Lesson')
  and attr_id in (select attr_id from attribute where attr_name like 'subject');*/
