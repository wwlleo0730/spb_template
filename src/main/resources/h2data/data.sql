DROP TABLE IF EXISTS unit;

CREATE TABLE unit
(
	id INT NOT NULL COMMENT 'pk',
	name VARCHAR(30) DEFAULT NULL COMMENT '部门名称',
	PRIMARY KEY (id)
);

----unit data
insert into unit (id,name) values (1 , 'develop');

DROP TABLE IF EXISTS station;

CREATE TABLE station
(
	id INT NOT NULL COMMENT 'pk',
	name VARCHAR(30) DEFAULT NULL COMMENT '职位名称',
	PRIMARY KEY (id)
);

----station data
insert into station (id,name) values (1,'java_engineer');
insert into station (id,name) values (2,'php_engineer');
insert into station (id,name) values (3,'ios_engineer');
insert into station (id,name) values (4,'java_senior_engineer');

DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id INT NOT NULL COMMENT 'pk',
	name VARCHAR(30) DEFAULT NULL COMMENT '姓名',
	station_id INT DEFAULT NULL COMMENT '职位ID',
	PRIMARY KEY (id)
);

----user data
insert into user(id,name,station_id) values (23,'jordan',1);
insert into user(id,name,station_id) values (24,'kobe',3);