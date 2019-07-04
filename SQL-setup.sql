-- Table: public.object_type

-- *********************** Entity_type (1) ********************

DROP TABLE if exists entity_type cascade;

CREATE TABLE entity_type
(
  ent_type_id serial NOT NULL,
  entity text NOT NULL,
  CONSTRAINT ent_type_pkey PRIMARY KEY (ent_type_id)
);

insert into entity_type (entity) values ('Student');
insert into entity_type (entity) values ('Teacher');
insert into entity_type (entity) values ('Lesson');
insert into entity_type (entity) values ('Admin');

select * from entity_type;


-- *********************** Attribute (3) ********************

DROP TABLE  if exists attribute CASCADE ;

CREATE TABLE attribute
(
  attr_id serial NOT NULL,
  attr_name text NOT NULL,
  CONSTRAINT attr_id_pkey PRIMARY KEY (attr_id)
);

insert into attribute (attr_name) values ('id');
insert into attribute (attr_name) values ('first_name');
insert into attribute (attr_name) values ('last_name');
insert into attribute (attr_name) values ('birth_date');
insert into attribute (attr_name) values ('price');
insert into attribute (attr_name) values ('lesson_date');
insert into attribute (attr_name) values ('academic_degree');
insert into attribute (attr_name) values ('subject');

select * from attribute;

-- *********************** Entity_Attribute (6) ********************

DROP TABLE if exists entity_attribute CASCADE;

CREATE TABLE entity_attribute
(
  entity_attribute_id serial NOT NULL,
  ent_type_id integer NOT NULL,
  attr_id integer NOT NULL,
  CONSTRAINT entity_attribute_id_pkey PRIMARY KEY (entity_attribute_id),
  CONSTRAINT ent_type_id_fkey foreign key (ent_type_id) references entity_type (ent_type_id) on delete cascade ,
  CONSTRAINT attr_id_fkey foreign key (attr_id) references attribute (attr_id) on delete cascade
);

insert into entity_attribute (ent_type_id, attr_id) values (1,1);
insert into entity_attribute (ent_type_id, attr_id) values (1,2);
insert into entity_attribute (ent_type_id, attr_id) values (1,3);
insert into entity_attribute (ent_type_id, attr_id) values (1,4);
insert into entity_attribute (ent_type_id, attr_id) values (2,1);
insert into entity_attribute (ent_type_id, attr_id) values (2,2);
insert into entity_attribute (ent_type_id, attr_id) values (2,3);
insert into entity_attribute (ent_type_id, attr_id) values (2,7);
insert into entity_attribute (ent_type_id, attr_id) values (3,1);
insert into entity_attribute (ent_type_id, attr_id) values (3,5);
insert into entity_attribute (ent_type_id, attr_id) values (3,6);
insert into entity_attribute (ent_type_id, attr_id) values (3,8);

select * from entity_attribute;

-- ****************** Object (2) (concrete objects match it's entity type) ****************
DROP TABLE IF EXISTS object CASCADE ;

CREATE TABLE object
(
  obj_id serial NOT NULL,
  ent_type_id integer NOT NULL,
  CONSTRAINT obj_id_pkey PRIMARY KEY (obj_id),
  CONSTRAINT ent_type_id_fkey foreign key (ent_type_id) references entity_type (ent_type_id) on delete cascade
);

insert into object (ent_type_id) values (1);
insert into object (ent_type_id) values (1);
insert into object (ent_type_id) values (1);
insert into object (ent_type_id) values (2);
insert into object (ent_type_id) values (2);
insert into object (ent_type_id) values (3);
insert into object (ent_type_id) values (3);

select * from object;

-- *********************** Value (4) *************************
DROP TABLE IF EXISTS value CASCADE ;

CREATE TABLE value
(
  val_id serial NOT NULL,
  obj_id integer NOT NULL,
  entity_attribute_id integer NOT NULL,
  val_int integer,
  val_text text,
  val_date date,
  val_numeric numeric(7,2),
  CONSTRAINT val_id_pkey PRIMARY KEY (val_id),
  CONSTRAINT obj_id_fkey foreign key (obj_id) references object (obj_id),
  CONSTRAINT entity_attribute_id_fkey foreign key (entity_attribute_id) references entity_attribute (entity_attribute_id)  on delete cascade
);

insert into value (obj_id, entity_attribute_id, val_int) values (2, 1, 2);
insert into value (obj_id, entity_attribute_id, val_text) values (2, 2, 'Иван');
insert into value (obj_id, entity_attribute_id, val_text) values (2, 3, 'Иванов');
insert into value (obj_id, entity_attribute_id, val_date) values (2, 4, to_date('05-08-1992', 'DD-MM-YYYY'));
insert into value (obj_id, entity_attribute_id, val_int) values (4, 5, 4);
insert into value (obj_id, entity_attribute_id, val_text) values (4, 6, 'Василий');
insert into value (obj_id, entity_attribute_id, val_text) values (4, 7, 'Петров');
insert into value (obj_id, entity_attribute_id, val_text) values (4, 8, 'профессор');
insert into value (obj_id, entity_attribute_id, val_int) values (6, 9, 6);
insert into value (obj_id, entity_attribute_id, val_numeric) values (6, 10, 650.0);
insert into value (obj_id, entity_attribute_id, val_date) values (6, 11, to_date('02-05-2019', 'DD-MM-YYYY'));
insert into value (obj_id, entity_attribute_id, val_text) values (6, 12, 'Математика 11 класс');

insert into value (obj_id, entity_attribute_id, val_int) values (1, 1, 1);
insert into value (obj_id, entity_attribute_id, val_text) values (1, 2, 'Федя');
insert into value (obj_id, entity_attribute_id, val_text) values (1, 3, 'Студентов');
insert into value (obj_id, entity_attribute_id, val_date) values (1, 4, to_date('03-08-1999', 'DD-MM-YYYY'));
insert into value (obj_id, entity_attribute_id, val_int) values (3, 1, 3);
insert into value (obj_id, entity_attribute_id, val_text) values (3, 2, 'Дима');
insert into value (obj_id, entity_attribute_id, val_text) values (3, 3, 'Учеников');
insert into value (obj_id, entity_attribute_id, val_date) values (3, 4, to_date('13-12-1997', 'DD-MM-YYYY'));
insert into value (obj_id, entity_attribute_id, val_int) values (5, 5, 5);
insert into value (obj_id, entity_attribute_id, val_text) values (5, 6, 'Николай');
insert into value (obj_id, entity_attribute_id, val_text) values (5, 7, 'Преподавательский');
insert into value (obj_id, entity_attribute_id, val_text) values (5, 8, 'доцент');
insert into value (obj_id, entity_attribute_id, val_int) values (7, 9, 7);
insert into value (obj_id, entity_attribute_id, val_numeric) values (7, 10, 850.0);
insert into value (obj_id, entity_attribute_id, val_date) values (7, 11, to_date('15-05-2019', 'DD-MM-YYYY'));
insert into value (obj_id, entity_attribute_id, val_text) values (7, 12, 'English 10 класс');

select * from value;

-- *********************** Lesson ************************

DROP TABLE IF EXISTS lesson CASCADE ;

CREATE TABLE lesson
(
  id serial NOT NULL,
  lesson_id integer NOT NULL,
  teacher_id integer NOT NULL,
  student_id integer NOT NULL,
  CONSTRAINT id_pkey PRIMARY KEY (id),
  CONSTRAINT lesson_id_fkey foreign key (lesson_id) references object (obj_id)  on delete cascade,
  CONSTRAINT teacher_id_fkey foreign key (teacher_id) references object (obj_id) on delete cascade,
  CONSTRAINT student_id_fkey foreign key (student_id) references object (obj_id) on delete cascade
);

insert into lesson (lesson_id, teacher_id, student_id) values (6, 4, 2);
insert into lesson (lesson_id, teacher_id, student_id) values (7, 5, 1);

select * from lesson;