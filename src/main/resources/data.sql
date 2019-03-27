insert into course(id, name, creation_date, update_date) 
values (4002, 'Maths', sysdate(), sysdate());
insert into course(id, name, creation_date, update_date) 
values (4003, 'Chemistry', sysdate(), sysdate());
insert into course(id, name, creation_date, update_date) 
values (4004, 'Biology', sysdate(), sysdate());
insert into course(id, name, creation_date, update_date) 
values (4005, 'Social Studies', sysdate(), sysdate());
insert into course(id, name, creation_date, update_date)  
values (4006, 'History', sysdate(), sysdate());
insert into course(id, name, creation_date, update_date) 
values (4007, 'English', sysdate(), sysdate());
insert into course(id, name, creation_date, update_date) 
values (4012, 'Economics', sysdate(), sysdate());


insert into passport(id, passport_number, creation_date, update_date) 
values (2001, 'Z125891', sysdate(), sysdate());
insert into passport(id, passport_number, creation_date, update_date) 
values (2002, 'H854215', sysdate(), sysdate());
insert into passport(id, passport_number, creation_date, update_date)
values (2003, 'K789511', sysdate(), sysdate());
insert into passport(id, passport_number, creation_date, update_date)
values (2004, 'H789211', sysdate(), sysdate());


insert into student(id, name, passport_id, creation_date, update_date) 
values (1001, 'Rahul',2003, sysdate(), sysdate());
insert into student(id, name, passport_id, creation_date, update_date) 
values (1002, 'Arko', 2001, sysdate(), sysdate());
insert into student(id, name, passport_id, creation_date, update_date) 
values (1003, 'Ramesh', 2004, sysdate(), sysdate());
insert into student(id, name, passport_id, creation_date, update_date) 
values (1004, 'Ram', 2001, sysdate(), sysdate());



insert into review(id, rating, description, course_id, creation_date, update_date)
values (3001, '4', 'Good course', 4002, sysdate(), sysdate());
insert into review(id, rating, description, course_id, creation_date, update_date)
values (3002, '2', 'Average couse',4002, sysdate(), sysdate());
insert into review(id, rating, description, course_id, creation_date, update_date)
values (3003, '1', 'Not good', 4004, sysdate(), sysdate());
insert into review(id, rating, description, course_id, creation_date, update_date)
values (3004, '5', 'Very good course', 4003, sysdate(), sysdate());
insert into review(id, rating, description, course_id, creation_date, update_date)
values (3005, '3', 'Average course', 4002, sysdate(), sysdate());
insert into review(id, rating, description, course_id, creation_date, update_date)
values (3006, '3', 'Average course', 4007, sysdate(), sysdate());
insert into review(id, rating, description, course_id, creation_date, update_date)
values (3007, '1', 'Not so good course', 4007, sysdate(), sysdate());


insert into student_course(student_id, course_id)
values(1004, 4005);
insert into student_course(student_id, course_id)
values(1001, 4002);
insert into student_course(student_id, course_id)
values(1001, 4006);
insert into student_course(student_id, course_id)
values(1002, 4005);