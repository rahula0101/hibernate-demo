create table IF NOT EXISTS course
(
id integer not null,
name varchar(255) not null,
creation_date TIMESTAMP not null,
update_date TIMESTAMP not null,
primary key(id)
);

create table IF NOT EXISTS passport
(
id integer not null,
passport_number varchar(255) not null,
creation_date TIMESTAMP not null,
update_date TIMESTAMP not null,
primary key(id)
);

create table IF NOT EXISTS student
(
id integer not null,
name varchar(255) not null,
passport_id integer not null,
creation_date TIMESTAMP not null,
update_date TIMESTAMP not null,
primary key(id),
constraint fk_passport_id foreign key (passport_id) references passport(id) 
);

create table IF NOT EXISTS review
(
id integer not null,
rating varchar(255) not null,
description varchar(255) not null,
course_id integer not null,
creation_date TIMESTAMP not null,
update_date TIMESTAMP not null,
primary key(id),
constraint fk_course_id foreign key (course_id) references course(id) 
);

create table IF NOT EXISTS student_course
(
student_id integer not null,
course_id integer not null, primary key(student_id,course_id), constraint fk_course_id_join foreign key (course_id) references course(id) ,
constraint fk_student_id_join foreign key (student_id) references student(id) 
);

create sequence id_course_gen;
create sequence id_student_gen;
create sequence id_passport_gen;
create sequence id_review_gen;