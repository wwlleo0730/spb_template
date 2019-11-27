----unit data
insert into unit (id,name) values (1 , 'develop');

----station data
insert into station (id,name,unit_id) values (1,'java_engineer',1);
insert into station (id,name,unit_id) values (2,'php_engineer',1);
insert into station (id,name,unit_id) values (3,'ios_engineer',1);
insert into station (id,name,unit_id) values (4,'java_senior_engineer',1);

----user data
insert into person(id,age,name,station_id,version) values (1,23,'jordan',1,0);
insert into person(id,age,name,station_id,version) values (2,24,'kobe',2,0);
insert into person(id,age,name,station_id,version) values (3,18,'jim',3,0);
insert into person(id,age,name,station_id,version) values (4,28,'ken',4,0);
insert into person(id,age,name,station_id,version) values (5,46,'bob',1,0);
insert into person(id,age,name,station_id,version) values (6,55,'alice',3,0);