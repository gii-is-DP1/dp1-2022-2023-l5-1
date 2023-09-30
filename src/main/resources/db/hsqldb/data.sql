INSERT INTO genres VALUES (1,'Adventure');
INSERT INTO genres VALUES (2,'MMO');
INSERT INTO genres VALUES (3,'JRPG');

INSERT INTO platforms VALUES (1,'PC');
INSERT INTO platforms VALUES (2,'Nintendo switch');
INSERT INTO platforms VALUES (3,'PS5');

INSERT INTO sagas VALUES (1,'The legend of Zelda');
INSERT INTO sagas VALUES (2,'Monster Hunter');
INSERT INTO sagas VALUES (3,'Battlefield');


-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture,version) VALUES ('admin1','4dm1n','Admin',TRUE,TRUE,'Gamer desde los 5','2001-01-15','Sevilla',1,1,1,'https://i.pinimg.com/550x/be/e6/fb/bee6fb846be933f3f7e041db460faf89.jpg',1);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture,version) VALUES ('player1','player1','Pepe',TRUE,FALSE,'Jugador apasidonado','2001-01-16','Huelva',2,2,3,'https://i.redd.it/4al31h1ehd431.png',1);
INSERT INTO authorities(id,username,authority) VALUES (2,'player1','player');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture,version) VALUES ('player2','player2','Paco',TRUE,TRUE,'Fan de los Zelda','2001-01-17','Malaga',1,2,2,'https://pbs.twimg.com/profile_images/1661045343587270657/fcwQSEQB_400x400.jpg',1);
INSERT INTO authorities(id,username,authority) VALUES (3,'player2','player');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture,version) VALUES ('player3','player3','Curro',TRUE,FALSE,'Tears of the kingdom videojuego del siglo','2001-01-18','Cordoba',3,3,3,'https://avatarfiles.alphacoders.com/103/thumb-103373.png',1);
INSERT INTO authorities(id,username,authority) VALUES (4,'player3','player');

INSERT INTO audits(id,start_date,end_date,user_username,in_progress,success,difficulty)
VALUES (1,'2022-06-27 10:11:37','2022-06-27 10:11:47','player1',FALSE,TRUE,0);

INSERT INTO audits(id,start_date,end_date,user_username,in_progress,success,difficulty)
VALUES (2,'2022-06-27 10:11:37',null,'player2',TRUE,null,1);


