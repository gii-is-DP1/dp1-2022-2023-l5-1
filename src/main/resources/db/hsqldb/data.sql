-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled,name,surname,email,profpicurl) VALUES ('admin1','4dm1n',TRUE,'Pepe','Lopez','pepe@mail.es','https://images.unsplash.com/photo-1554080353-a576cf803bda?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8cGhvdG9ncmFwaHl8ZW58MHx8MHx8&w=1000&q=80');
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled,name,surname,email,profpicurl) VALUES ('owner1','0wn3r',TRUE,'Pepe1','Lopez1','pepe1@mail.es','https://images.unsplash.com/photo-1566275529824-cca6d008f3da?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8cGhvdG98ZW58MHx8MHx8&w=1000&q=80');
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled,name,surname,email,profpicurl) VALUES ('vet1','v3t',TRUE,'Pepe2','Lopez2','pepe2@mail.es','https://www.befunky.com/images/prismic/5ddfea42-7377-4bef-9ac4-f3bd407d52ab_landing-photo-to-cartoon-img5.jpeg?auto=avif,webp&format=jpg&width=863');
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO vets(id, first_name,last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets(id, first_name,last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets(id, first_name,last_name) VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets(id, first_name,last_name) VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets(id, first_name,last_name) VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets(id, first_name,last_name) VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');
INSERT INTO types VALUES (7, 'turtle');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT INTO players(id,nickname,resistance,honor,rol_id,user_id) VALUES (0,'ParejoSenpai',4,6,1,1);

INSERT INTO games VALUES ('QWERTY', 'owner1');
INSERT INTO games VALUES ('QWERTY2', 'admin1');

INSERT INTO rol(id,name,stars) VALUES (0, 'ninja1',1 );
INSERT INTO rol(id,name,stars) VALUES (1, 'ninja2',2 );
INSERT INTO rol(id,name,stars) VALUES (2, 'ninja3',3);
INSERT INTO rol(id,name,stars) VALUES (3, 'ronin',1 );
INSERT INTO rol(id,name,stars) VALUES (4, 'samurai',1 );
INSERT INTO rol(id,name,stars) VALUES (5, 'shogun',1 );

INSERT INTO acciones(id,name,description,cardImage,CardType) VALUES (0, 'respiración','Recuperas todos tus puntos de Resistencia (es decir, te curas de todas tus heridas). Entonces, otro jugador que elijas (¡tú no!) roba 1 carta del mazo. No puedes curar a otros jugadores.', '', UTILITY );
INSERT INTO acciones(id,name,description,cardImage,CardType) VALUES (1 , 'grito de batalla','Cada uno de los demás jugadores debe elegir entre jugar una Parada o sufrir 1 herida. Los jugadores Inofensivos no se ven afectados por el Grito de batalla.', '', UTILITY );
INSERT INTO acciones(id,name,description,cardImage,CardType) VALUES (2 , 'jiu-jitsu','Cada uno de los demás jugadores debe elegir entre jugar 1 carta de Arma o sufrir 1 herida. Los jugadores Inofensivos no se ven afectados por Jiu-jitsu.', '', UTILITY );
INSERT INTO acciones(id,name,description,cardImage,CardType) VALUES (3 , 'ceremonia del té','Roba 3 cartas del mazo. Cada uno de los demás jugadores roba 1 carta del mazo.', '', UTILITY );
INSERT INTO acciones(id,name,description,cardImage,CardType) VALUES (4 , 'geisha','Obliga a un jugador a descartar 1 carta (independientemente de la Dificultad). Puedes elegir cualquier carta de Propiedad que tenga en juego, o cogerla al azar de la mano del jugador.', '', UTILITY );
INSERT INTO acciones(id,name,description,cardImage,CardType) VALUES (5 , 'distracción','Roba 1 carta al azar de la mano de otro jugador (independientemente de la Dificultad) y añádela a tu mano.', '', UTILITY );
INSERT INTO acciones(id,name,description,cardImage,CardType) VALUES (6, 'parada','Puedes usar esta carta cuando te ataquen y parar el ataque para impedir que te hagan daño', '', UTILITY );
