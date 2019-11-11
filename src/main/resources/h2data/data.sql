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
insert into user(id,name,age,station_id) values (1,23,'jordan',1);
insert into user(id,name,age,station_id) values (2,24,'kobe',2);
insert into user(id,name,age,station_id) values (3,18,'jim',3);
insert into user(id,name,age,station_id) values (4,28,'ken',4);
insert into user(id,name,age,station_id) values (5,29,'bob',1);
insert into user(id,name,age,station_id) values (6,37,'haha',3);