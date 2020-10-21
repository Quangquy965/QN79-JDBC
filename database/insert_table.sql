use newservlet2020;

insert into role(code, name) values('ADMIN','ADMIN');
insert into role(code, name) values('USER','USER');

insert into user(username,password,fullname,status, roleid) values('admin','1234','admin',1,1);
insert into user(username,password,fullname,status, roleid) values('nguyenhuua','1234','Nguyen Huu A',1,2);
insert into user(username,password,fullname,status, roleid) values('nguyenhuub','1234','Nguyen Huu B',1,2);
