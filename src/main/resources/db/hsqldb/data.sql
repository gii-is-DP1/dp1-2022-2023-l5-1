-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,name,enabled,hardcoregamer) VALUES ('admin1','4dm1n','Admin',TRUE,TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

INSERT INTO users(username,password,name,enabled,hardcoregamer) VALUES ('player1','player1','Pepe',TRUE,FALSE);
INSERT INTO authorities(id,username,authority) VALUES (2,'player1','player');


INSERT INTO users(username,password,name,enabled,hardcoregamer) VALUES ('player2','player2','Paco',TRUE,FALSE);
INSERT INTO authorities(id,username,authority) VALUES (3,'player2','player');
INSERT INTO users(username,password,name,enabled,hardcoregamer) VALUES ('player3','player3','Curro',TRUE,TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'player3','player');