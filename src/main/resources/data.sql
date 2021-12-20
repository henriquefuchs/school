insert into User (username, email) values ('alex', 'alex@email.com');
insert into User (username, email) values ('ana', 'ana@email.com');
insert into User (username, email) values ('nico', 'nico@email.com');

insert into Course (code, name, description) values ('java-1', 'Java OO', 'Java and Object Orientation: Encapsulation, Inheritance and Polymorphism.');
insert into Course (code, name, description) values ('java-2', 'Java Collections', 'Java Collections: Lists, Sets, Maps and more.');
insert into Course (code, name, description) values ('java-3', 'Java Collections 2', 'More Java Collections.');

insert into Enrollment (user_id, course_id, created_on) values (1,1, NOW());
insert into Enrollment (user_id, course_id, created_on) values (2,1, NOW());
insert into Enrollment (user_id, course_id, created_on) values (2,2, NOW());
insert into Enrollment (user_id, course_id, created_on) values (2,3, NOW());