INSERT INTO genres VALUES (1,'Adventure');
INSERT INTO genres VALUES (2,'MMO');
INSERT INTO genres VALUES (3,'JRPG'),(4,'Action'),(5,'Arcade'),(6,'Sports'),(8,'Strategy'),(9,'Souls like'),(10,'Strategy'),(11,'Classic RPG'), (12,'Classic RPG');

INSERT INTO platforms VALUES (1,'PC');
INSERT INTO platforms VALUES (2,'Nintendo switch');
INSERT INTO platforms VALUES (3,'PS5');
INSERT INTO platforms VALUES (4, 'Xbox Series X');
INSERT INTO platforms VALUES (5, 'PlayStation 4');
INSERT INTO platforms VALUES (6, 'Xbox One');
INSERT INTO platforms VALUES (7, 'PlayStation 3');
INSERT INTO platforms VALUES (8, 'Nintendo 3DS');
INSERT INTO platforms VALUES (9, 'Wii U');
INSERT INTO platforms VALUES (10, 'PlayStation Vita');
INSERT INTO platforms VALUES (11, 'Nintendo DS');
INSERT INTO platforms VALUES (12, 'PSP');
INSERT INTO platforms VALUES (13, 'Mobile');

INSERT INTO sagas VALUES (1,'The legend of Zelda');
INSERT INTO sagas VALUES (2,'Monster Hunter');
INSERT INTO sagas VALUES (3,'Battlefield');
INSERT INTO sagas VALUES (4, 'Final Fantasy');
INSERT INTO sagas VALUES (5, 'Call of Duty');
INSERT INTO sagas VALUES (6, 'Assassins Creed');
INSERT INTO sagas VALUES (7, 'The Elder Scrolls');
INSERT INTO sagas VALUES (8, 'Pokemon');
INSERT INTO sagas VALUES (9, 'Mass Effect');
INSERT INTO sagas VALUES (10, 'Metal Gear Solid');
INSERT INTO sagas VALUES (11, 'Super Mario');
INSERT INTO sagas VALUES (12, 'Halo');
INSERT INTO sagas VALUES (13, 'Resident Evil');


-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('admin1','4dm1n','Admin',TRUE,TRUE,'Gamer desde los 5','2001-01-15','Sevilla',1,1,1,'https://i.pinimg.com/550x/be/e6/fb/bee6fb846be933f3f7e041db460faf89.jpg');
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('player1','player1','Pepe',TRUE,FALSE,'Jugador apasidonado','2001-01-16','Huelva',2,2,3,'https://i.redd.it/4al31h1ehd431.png');
INSERT INTO authorities(id,username,authority) VALUES (2,'player1','player');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('player2','player2','Paco',TRUE,TRUE,'Fan de los Zelda','2001-01-17','Malaga',1,2,2,'https://pbs.twimg.com/profile_images/1661045343587270657/fcwQSEQB_400x400.jpg');
INSERT INTO authorities(id,username,authority) VALUES (3,'player2','player');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('player3','player3','Curro',TRUE,FALSE,'Tears of the kingdom videojuego del siglo','2001-01-18','Cordoba',3,3,3,'https://avatarfiles.alphacoders.com/103/thumb-103373.png');
INSERT INTO authorities(id,username,authority) VALUES (4,'player3','player');

--Datos de relleno generados con chatGPT para probar la paginación
INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('player4','player4','Alice',TRUE,FALSE,'Gamer and adventurer in the gaming realm.','1992-08-27','London',4,4,4,'https://avatarfiles.alphacoders.com/104/thumb-104374.png');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('game_lover5','game_lover5','Michael',TRUE,TRUE,'Lover of all things gaming.','1985-04-12','Los Angeles',5,5,5,'https://avatarfiles.alphacoders.com/105/thumb-105375.png');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('gamer6','gamer6','Sophia',TRUE,FALSE,'Casual gamer and tech enthusiast.','1998-11-03','New York',6,6,6,'https://avatarfiles.alphacoders.com/106/thumb-106376.png');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('playmaster7','playmaster7','David',TRUE,TRUE,'Mastering games is my forte.','1994-02-19','San Francisco',8,8,8,'https://avatarfiles.alphacoders.com/107/thumb-107377.png');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('videogamer8','videogamer8','Olivia',TRUE,FALSE,'Diving into virtual worlds since childhood.','1989-07-31','Berlin',8,8,8,'https://avatarfiles.alphacoders.com/108/thumb-108378.png');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('gamefanatic9','gamefanatic9','Daniel',TRUE,TRUE,'Gaming is my passion and my profession.','1987-06-25','Tokyo',9,9,9,'https://avatarfiles.alphacoders.com/109/thumb-109379.png');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('gaming_enthusiast10','gaming_enthusiast10','Emma',TRUE,FALSE,'Always up for a gaming challenge.','1996-09-14','Paris',10,10,10,'https://avatarfiles.alphacoders.com/110/thumb-110380.png');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('playtime11','playtime11','Lucas',TRUE,TRUE,'Every moment is playtime.','1993-12-08','Barcelona',11,11,11,'https://avatarfiles.alphacoders.com/111/thumb-111381.png');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('gamerlife12','gamerlife12','Isabella',TRUE,FALSE,'Living the gamer life.','1991-05-20','Sydney',12,12,12,'https://avatarfiles.alphacoders.com/112/thumb-112382.png');

INSERT INTO users(username,password,name,enabled,hardcoregamer,biography,birth_date,location,genre_id,saga_id,platform_id,profile_picture) VALUES ('gamejunkie13','gamejunkie13','Nathan',TRUE,TRUE,'Addicted to gaming.','1986-10-17','Toronto',12,12,12,'https://avatarfiles.alphacoders.com/113/thumb-113383.png');

INSERT INTO authorities(id,username,authority) VALUES (5,'player4','player');
INSERT INTO authorities(id,username,authority) VALUES (6,'game_lover5','player');
INSERT INTO authorities(id,username,authority) VALUES (7,'gamer6','player');
INSERT INTO authorities(id,username,authority) VALUES (8,'playmaster7','player');
INSERT INTO authorities(id,username,authority) VALUES (9,'videogamer8','player');
INSERT INTO authorities(id,username,authority) VALUES (10,'gamefanatic9','player');
INSERT INTO authorities(id,username,authority) VALUES (11,'gaming_enthusiast10','player');
INSERT INTO authorities(id,username,authority) VALUES (12,'playtime11','player');
INSERT INTO authorities(id,username,authority) VALUES (13,'gamerlife12','player');
INSERT INTO authorities(id,username,authority) VALUES (14,'gamejunkie13','player');

--Fin de los datos de relleno
INSERT INTO audits(id,start_date,end_date,user_username,in_progress,success,difficulty)
VALUES (1,'2022-06-27 10:11:37','2022-06-27 10:11:47','player1',FALSE,TRUE,0),(2,'2022-06-27 10:11:37',null,'player2',TRUE,null,1),(3,'2022-06-27 10:11:37',null,'player2',TRUE,null,1),(4,'2022-06-27 10:11:37',null,'player2',TRUE,null,1),(5,'2022-06-27 10:11:37',null,'player2',TRUE,null,1),(6,'2022-06-27 10:11:37',null,'player2',TRUE,null,1),(7,'2022-06-27 10:11:37',null,'player2',TRUE,null,1),
(8,'2022-06-27 10:11:37',null,'player2',TRUE,null,1),(9,'2022-06-27 10:11:37',null,'player2',TRUE,null,1),(10,'2022-06-27 10:11:37',null,'player2',TRUE,null,1),(11,'2022-06-27 10:11:37',null,'player2',TRUE,null,1),(12,'2022-06-27 10:11:37',null,'player2',TRUE,null,1);




