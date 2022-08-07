create table UTILISATEUR (
pseudo VARCHAR(50) NOT NULL,
mail VARCHAR(50)NOT NULL,
nom VARCHAR(20),
prenom VARCHAR(20),
    CONSTRAINT PK_UTILISATEUR PRIMARY KEY (pseudo)
);

create table SESSION (
id_session int generated always as identity,
nom VARCHAR(50)NOT NULL,
date_creation date,
pseudo_createur VARCHAR(20),
    CONSTRAINT PK_SESSION PRIMARY KEY (id_session),
		CONSTRAINT FK_SESSION_UTILISATEUR FOREIGN KEY(pseudo_createur) REFERENCES UTILISATEUR(pseudo)
);

create table PARTICIPER (
pseudo VARCHAR(50),
id_session int,
    CONSTRAINT PK_PARTICIPER PRIMARY KEY(pseudo, id_session),
	CONSTRAINT FK_PARTICIPER_UTILISATEUR FOREIGN KEY(pseudo) REFERENCES UTILISATEUR(pseudo),
	CONSTRAINT FK_PARTICIPER_SESSION FOREIGN KEY(id_session) REFERENCES SESSION(id_session)
);

create table MATCH (
id_match int generated always as identity,
equipe1 VARCHAR(50) NOT NULL,
equipe2 VARCHAR(50) NOT NULL,
score_equipe1 int,
score_equipe2 int,
date_match date,
id_session int,
    CONSTRAINT PK_MATCH PRIMARY KEY (id_match),
	CONSTRAINT FK_MATCH_SESSION FOREIGN KEY(id_session) REFERENCES SESSION(id_session)
);

create table PARI (
id_pari int generated always as identity,
pari_equipe1 int,
pari_equipe2 int,
pseudo VARCHAR(50),
id_match int,
    CONSTRAINT PK_PARI PRIMARY KEY(pseudo, id_match),
	CONSTRAINT FK_PARI_UTILISATEUR FOREIGN KEY(pseudo) REFERENCES UTILISATEUR(pseudo),
	CONSTRAINT FK_PARI_MATCH FOREIGN KEY(id_match) REFERENCES MATCH(id_match)
);


----Table Utilisateur:
INSERT INTO utilisateur(pseudo,mail,nom,prenom) VALUES
('Tommy','tomagassi@gmail.com','agassi','Tom'),
('Mat','matsavoldello@gmail.com','savoldello','Mat'),
('Lalou', 'lalou@gmail.com','Bonn','Laurie'),
('Roro', 'romlleres@gmail.com', 'Lleres','Rom'),
('Lily', 'lilromaneti@gmail.com', 'romaneti', 'lily'),
('Miloud', 'mimi52@orange.fr', 'sazil', 'Moan'),
('Zaza', 'oalala568@sfr.fr', 'zartek', 'Lou'),
('Wiss', 'swissthu26@hotmail.com', 'Swissah', 'Norwan'),
('Bibo', 'bibi65@eff.fr', 'Tetine', 'Bibine');

----Table SESSION :
INSERT INTO session(nom,date_creation,pseudo_createur) VALUES
('Euro Foot 2022', '2022-02-17','Tommy'),
('Ligue des champions 2022', '2022-02-15', 'Mat'),
('Coupe du monde de rugby 2023', '2022-03-04', 'Lily'),
('6 nations 2022', '2022-02-14', 'Roro'),
('NBA', '2022-02-02', 'Lalou');



--Table Match:

truncate  match cascade;


INSERT INTO match(equipe1, equipe2,score_equipe1, score_equipe2, date_match, id_session) 
(select 'Manchester United', 'Chelsea', 1, 2, Date('2022-03-01'), id_session 
 from Session where nom='Ligue des champions 2022'
 UNION
 select 'Real Madrid', 'FC Barcelone', 0, 0, Date('2022-03-05'), id_session 
 from Session where nom='Ligue des champions 2022'
 UNION
 select 'Bayern', 'PSG', 1, 3, Date('2022-03-06'), id_session 
 from Session where nom='Ligue des champions 2022');
 
 INSERT INTO match(equipe1, equipe2,score_equipe1, score_equipe2, date_match, id_session) 
(select 'Angleterre', 'Allemagne',NULL::integer, NULL::integer, Date('2022-09-30'), id_session 
 from Session where nom='Euro Foot 2022'
 UNION
 select 'France', 'ESpagne',NULL::integer, NULL::integer, Date('2022-10-03'), id_session 
 from Session where nom='Euro Foot 2022'
 UNION
 select 'Islande', 'Portugal',NULL::integer, NULL::integer, Date('2022-10-06'), id_session 
 from Session where nom='Euro Foot 2022');
 
 
 INSERT INTO match(equipe1, equipe2,score_equipe1, score_equipe2, date_match, id_session) 
(select 'Angleterre', 'Ecosse', NULL::integer, NULL::integer, Date('2022-09-30'), id_session 
 from Session where nom='6 nations 2022'
 UNION
 select 'France', 'Italie', NULL::integer, NULL::integer, Date('2022-10-03'), id_session 
 from Session where nom='6 nations 2022'
 UNION
 select 'Irlande', 'Pays de Galle', NULL::integer, NULL::integer, Date('2022-10-06'), id_session 
 from Session where nom='6 nations 2022');
 
 INSERT INTO match(equipe1, equipe2,score_equipe1, score_equipe2, date_match, id_session) 
(select 'Angleterre', 'Nouvelle Zelande', NULL::integer, NULL::integer, Date('2022-11-20'), id_session 
 from Session where nom='Coupe du monde de rugby 2022'
 UNION
 select 'France', 'Argentine', NULL::integer, NULL::integer, Date('2022-11-18'), id_session 
 from Session where nom='Coupe du monde de rugby 2022'
 UNION
 select 'Australie', 'Pays de Galle', NULL::integer, NULL::integer, Date('2022-11-15'), id_session 
 from Session where nom='Coupe du monde de rugby 2022');
 
 INSERT INTO match(equipe1, equipe2,score_equipe1, score_equipe2, date_match, id_session) 
(select 'Los Angeles', 'Chicago', NULL::integer, NULL::integer, Date('2022-11-20'), id_session 
 from Session where nom='NBA'
 UNION
 select 'New York', 'San Fransisco', NULL::integer, NULL::integer, Date('2022-11-18'), id_session 
 from Session where nom='NBA');

----Table Pari:
Truncate pari cascade;

INSERT INTO pari(pari_equipe1,pari_equipe2,pseudo,id_match) 
(Select 3,1,'Mat', id_match FROM Match WHERE equipe1='Real Madrid' AND equipe2='FC Barcelone'
 UNION
 Select 1,1,'Mat', id_match FROM Match WHERE equipe1='Manchester United' AND equipe2='Chelsea'
 UNION
 Select 3,0,'Mat', id_match FROM Match WHERE equipe1='Bayern' AND equipe2='PSG'
 UNION
Select 0,1,'Miloud', id_match FROM Match WHERE equipe1='Real Madrid' AND equipe2='FC Barcelone'
 UNION
 Select 3,0,'Miloud', id_match FROM Match WHERE equipe1='Manchester United' AND equipe2='Chelsea'
 UNION
 Select 0,0,'Miloud', id_match FROM Match WHERE equipe1='Bayern' AND equipe2='PSG'
UNION
Select 0,0,'Zaza', id_match FROM Match WHERE equipe1='Real Madrid' AND equipe2='FC Barcelone'
 UNION
 Select 1,4,'Zaza', id_match FROM Match WHERE equipe1='Manchester United' AND equipe2='Chelsea'
 UNION
 Select 2,0,'Zaza', id_match FROM Match WHERE equipe1='Bayern' AND equipe2='PSG'
UNION
Select 0,3,'Wiss', id_match FROM Match WHERE equipe1='Real Madrid' AND equipe2='FC Barcelone'
 UNION
 Select 0,0,'Wiss', id_match FROM Match WHERE equipe1='Manchester United' AND equipe2='Chelsea'
 UNION
 Select 3,1,'Wiss', id_match FROM Match WHERE equipe1='Bayern' AND equipe2='PSG'
UNION
Select 1,1,'Bibo', id_match FROM Match WHERE equipe1='Real Madrid' AND equipe2='FC Barcelone'
 UNION
 Select 2,1,'Bibo', id_match FROM Match WHERE equipe1='Manchester United' AND equipe2='Chelsea'
 UNION
 Select 3,2,'Bibo', id_match FROM Match WHERE equipe1='Bayern' AND equipe2='PSG'
 UNION
Select 1,0,'Tommy', id_match FROM Match WHERE equipe1='France' AND equipe2='ESpagne'
 UNION
 Select 3,1,'Tommy', id_match FROM Match WHERE equipe1='Angleterre' AND equipe2='Allemagne'
 UNION
 Select 2,2,'Tommy', id_match FROM Match WHERE equipe1='Islande' AND equipe2='Portugal'
 UNION
Select 18,0,'Roro', id_match FROM Match WHERE equipe1='France' AND equipe2='Italie'
 UNION
 Select 5,5,'Roro', id_match FROM Match WHERE equipe1='Angleterre' AND equipe2='Ecosse'
 UNION
 Select 23,12,'Roro', id_match FROM Match WHERE equipe1='Irlande' AND equipe2='Pays de Galle'
);

----Table participer:

Truncate participer cascade;

INSERT INTO participer(pseudo,id_session) 
(SELECT 'Tommy',id_session FROM Session WHERE nom='Euro Foot 2022'
 UNION
 Select 'Mat', id_session FROM Session WHERE nom='Ligue des champions 2022'
UNION
 Select 'Lalou', id_session FROM Session WHERE nom='NBA'
 UNION
 Select 'Lily', id_session FROM Session WHERE nom='Coupe du monde de rugby 2023'
 UNION
 Select 'Roro', id_session FROM Session WHERE nom='6 nations 2022'
 UNION
 Select 'Miloud', id_session FROM Session WHERE nom='Ligue des champions 2022'
 UNION
 Select 'Zaza', id_session FROM Session WHERE nom='Ligue des champions 2022'
 UNION
 Select 'Wiss', id_session FROM Session WHERE nom='Ligue des champions 2022'
 UNION
 Select 'Bibo', id_session FROM Session WHERE nom='Ligue des champions 2022');
 
 
---- VUE 

CREATE VIEW v_paris AS
SELECT p.pseudo, m.equipe1, p.pari_equipe1, m.equipe2, p.pari_equipe2, s.nom from pari p, match m, session s
WHERE  m.id_match = p.id_match
AND s.id_session = m.id_session
ORDER BY p.pseudo;