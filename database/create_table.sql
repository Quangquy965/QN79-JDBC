use newservlet2020;

create table role(
 id bigint NOT NULL PRIMARY KEY auto_increment,
 name VARCHAR(255) NOT NULL,
 code VARCHAR(255 ) NOT NULL,
 createddate TIMESTAMP NULL,
 modifieddate timestamp NULL,
 createdby VARCHAR(255) NULL,
 modifiedby VARCHAR(255) NULL
);

create table user(
 id bigint NOT NULL PRIMARY KEY auto_increment,
 username VARCHAR(255) NOT NULL,
 password VARCHAR(255) NOT NULL,
 fullname VARCHAR(255) NULL,
 status int NOT NULL,
 roleid bigint NOT NULL,
 createddate TIMESTAMP NULL,
 modifieddate timestamp NULL
);

ALTER TABLE user ADD CONSTRAINT fk_user_role FOREIGN KEY (roleid) REFERENCES user(id);

create table news(
 id bigint NOT NULL PRIMARY KEY auto_increment,
 title VARCHAR(255) NULL,
 thumbnail VARCHAR(255) NULL,
 shortdescription text NULL,
 content text NULL, 
 categoryid bigint NOT NULL,
 createddate TIMESTAMP NULL,
 modifieddate timestamp NULL,
 createdby VARCHAR(255) NULL,
 modifiedby VARCHAR(255) NULL
);

create table category(
 id bigint NOT NULL PRIMARY KEY auto_increment,
 name VARCHAR(255) NOT NULL,
 code VARCHAR(255) NOT NULL,
 createddate TIMESTAMP NULL,
 modifieddate timestamp NULL,
 createdby VARCHAR(255) NULL,
 modifiedby VARCHAR(255) NULL
);

ALTER TABLE news ADD CONSTRAINT fk_news_category FOREIGN KEY (categoryid) REFERENCES category(id);

create table comment(
 id bigint NOT NULL PRIMARY KEY auto_increment,
 content text NOT NULL,
 user_id bigint NOT NULL,
 news_id bigint NOT NULL,
 createddate TIMESTAMP NULL,
 modifieddate timestamp NULL,
 createdby VARCHAR(255) NULL,
 modifiedby VARCHAR(255) NULL
);

ALTER TABLE comment ADD CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES user(id);
ALTER TABLE comment ADD CONSTRAINT fk_comment_news FOREIGN KEY (news_id) REFERENCES news(id);