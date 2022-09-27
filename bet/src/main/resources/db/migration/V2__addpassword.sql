ALTER table UTILISATEUR ADD COLUMN password VARCHAR(200);
INSERT INTO utilisateur(pseudo,mail,nom,prenom,password) VALUES
('admin','admin@gmail.com','admin','admin','admin');