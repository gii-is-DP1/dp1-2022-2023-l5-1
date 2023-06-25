-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,name,enabled) VALUES ('admin1','4dm1n','Admin',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

INSERT INTO users(username,password,name,enabled) VALUES ('player1','player1','Pepe',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'player1','player');

