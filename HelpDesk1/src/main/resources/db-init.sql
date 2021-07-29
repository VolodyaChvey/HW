insert into user (FIRST_NAME, LAST_NAME, ROLE_ID, EMAIL, PASSWORD) values
('User1Name', 'User1Surname', 'EMPLOYEE', 'user1_mogilev@yopmail.com', 'UEBzc3dvcmQx');
insert into user (FIRST_NAME, LAST_NAME, ROLE_ID, EMAIL, PASSWORD) values
('User2Name', 'User2Surname', 'EMPLOYEE', 'user2_mogilev@yopmail.com', 'UEBzc3dvcmQx');
insert into user (FIRST_NAME, LAST_NAME, ROLE_ID, EMAIL, PASSWORD) values
('Manager1Name', 'Manager1Surname', 'MANAGER', 'manager1_mogilev@yopmail.com', 'UEBzc3dvcmQx');
insert into user (FIRST_NAME, LAST_NAME, ROLE_ID, EMAIL, PASSWORD) values
('Manager2Name', 'Manager2Surname', 'MANAGER', 'manager2_mogilev@yopmail.com', 'UEBzc3dvcmQx');
insert into user (FIRST_NAME, LAST_NAME, ROLE_ID, EMAIL, PASSWORD) values
('Engineer1Name', 'Engineer1Surname', 'ENGINEER', 'engineer1_mogilev@yopmail.com', 'UEBzc3dvcmQx');
insert into user (FIRST_NAME, LAST_NAME, ROLE_ID, EMAIL, PASSWORD) values
('Engineer2Name', 'Engineer2Surname', 'ENGINEER', 'engineer2_mogilev@yopmail.com', 'UEBzc3dvcmQx');


insert into category (NAME) values ('Application & Services');
insert into category (NAME) values ('Benefits & Paper Work');
insert into category (NAME) values ('Hardware & Software');
insert into category (NAME) values ('People Management');
insert into category (NAME) values ('Security & Access');
insert into category (NAME) values ('Workplaces & Facilities');


insert into ticket(NAME, DESCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, STATE, URGENCY, OWNER_ID, CATEGORY_ID) values
('ticket1', 'description1', '2010-10-10', '2010-10-15', 'DONE', '1', '1', '1');
insert into ticket(NAME, DESCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, STATE, URGENCY, OWNER_ID, CATEGORY_ID) values
('ticket2', 'description2', '2010-12-11', '2010-12-25', 'DRAFT', '1', '1', '2');
insert into ticket(NAME, DESCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, STATE, URGENCY, OWNER_ID, CATEGORY_ID) values
('ticket3', 'description3', '2010-12-31', '2011-01-05', 'NEW', '2', '2', '2');
insert into ticket(NAME, DESCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, STATE, URGENCY, OWNER_ID, CATEGORY_ID) values
('ticket4', 'description4', '2010-06-30', '2010-10-15', 'APPROVED', '3', '3', '4');
insert into ticket(NAME, DESCRIPTION, CREATED_ON, DESIRED_RESOLUTION_DATE, STATE, URGENCY, OWNER_ID, CATEGORY_ID) values
('ticket5', 'description5', '2009-10-10', '2010-10-10', 'NEW', '4', '4', '6');


insert into comment(DATE, TEXT, TICKET_ID, USER_ID) values
('2018-08-20', 'comment1', 1, 1);
insert into comment(DATE, TEXT, TICKET_ID, USER_ID) values
('2018-08-22', 'comment2', 1, 1);
insert into comment(DATE, TEXT, TICKET_ID, USER_ID) values
('2018-08-15', 'comment3', 1, 1);
insert into comment(DATE, TEXT, TICKET_ID, USER_ID) values
('2017-08-22', 'comment4', 1, 1);
insert into comment(DATE, TEXT, TICKET_ID, USER_ID) values
('2018-08-22', 'comment5', 1, 1);
insert into comment(DATE, TEXT, TICKET_ID, USER_ID) values
('2018-08-22', 'comment6', 1, 1);
insert into comment(DATE, TEXT, TICKET_ID, USER_ID) values
('2018-08-22', 'comment7', 1, 1);


insert into history(DATE,ACTION,DESCRIPTION,TICKET_ID,USER_ID) values
('2018-08-20', 'action1', 'descr1', 1, 1);
insert into history(DATE,ACTION,DESCRIPTION,TICKET_ID,USER_ID) values
('2018-08-22', 'action2', 'descr', 1, 2);
insert into history(DATE,ACTION,DESCRIPTION,TICKET_ID,USER_ID) values
('2017-08-20', 'action3', 'descr', 1, 1);
insert into history(DATE,ACTION,DESCRIPTION,TICKET_ID,USER_ID) values
('2016-08-20', 'action4', 'descr', 1, 3);
insert into history(DATE,ACTION,DESCRIPTION,TICKET_ID,USER_ID) values
('2015-08-20', 'action5', 'descr', 1, 4);
insert into history(DATE,ACTION,DESCRIPTION,TICKET_ID,USER_ID) values
('2014-08-20', 'action6', 'descr', 1, 5);
insert into history(DATE,ACTION,DESCRIPTION,TICKET_ID,USER_ID) values
('2013-08-20', 'action7', 'descr', 1, 6);