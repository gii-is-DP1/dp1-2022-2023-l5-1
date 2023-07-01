-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,name,enabled) VALUES ('admin1','4dm1n','Admin',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

INSERT INTO users(username,password,name,enabled) VALUES ('player1','player1','Pepe',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'player1','player');

INSERT INTO genres(id,name) VALUES (1,'genre1');
INSERT INTO genres(id,name) VALUES (2,'genre2');
INSERT INTO genres(id,name) VALUES (3,'genre3');

INSERT INTO platforms(id,name) VALUES (1,'platform1');
INSERT INTO platforms(id,name) VALUES (2,'platform2');
INSERT INTO platforms(id,name) VALUES (3,'platform3');

INSERT INTO sagas(id,name) VALUES (1,'saga1');
INSERT INTO sagas(id,name) VALUES (2,'saga2');
INSERT INTO sagas(id,name) VALUES (3,'saga3');