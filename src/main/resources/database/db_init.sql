DROP TABLE IF EXISTS users;
CREATE TABLE users (
  userID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  userName VARCHAR(20) NOT NULL,
  pwdHash VARCHAR(100) NOT NULL
);

INSERT INTO user VALUES (0,"tomaz","tomaz");
INSERT INTO user VALUES (0,"franci","franci");
INSERT INTO user VALUES (0,"nejc","nejc");
INSERT INTO user VALUES (0,"alenka","alenka");
-- SELECT * FROM user;

DROP TABLE IF EXISTS opravilo;
CREATE TABLE opravilo(
  opraviloID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  opraviloNaziv VARCHAR(20) NOT NULL,
  opraviloOpis VARCHAR(100) NOT NULL
);

INSERT INTO opravilo VALUES (0,"KUHINJA STEDILNIK","Obrisi stedilnik.");
INSERT INTO opravilo VALUES (1,"KUHINJA PULT","Obrisi pult.");
INSERT INTO opravilo VALUES (2,"KUHINJA LIJAK","Obrisi lijak.");
INSERT INTO opravilo VALUES (3,"KUHINJA TLA","Pometi tla.");
INSERT INTO opravilo VALUES (4,"KUHINJA SMETI","Odnesi smeti.");

INSERT INTO opravilo VALUES (5,"DNEVNA MIZA","Pobrisi mizo.");
INSERT INTO opravilo VALUES (6,"DNEVNA TLA","Pometi tla..");

INSERT INTO opravilo VALUES (7,"WC LIJAK","Obrisi lijak.");
INSERT INTO opravilo VALUES (8,"WC SKOLJKA","Obrisi skoljko.");
INSERT INTO opravilo VALUES (9,"WC TLA","Pobrisi tla.");

INSERT INTO opravilo VALUES (10,"HODNIK", "Uredi hodnik.");
INSERT INTO opravilo VALUES (11,"BALKON PEPELNIKI", "Sprazni pepelnike.");
INSERT INTO opravilo VALUES (12,"BALKON TLA", "Pometi tla.");


DROP TABLE IF EXISTS teden;
CREATE TABLE teden(
  tedenID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  userID INT NOT NULL,
  dateStart DATE,
  dateEnd DATE,
  FOREIGN KEY (userID) REFERENCES users(userID)
);

INSERT INTO teden VALUES (1,1,null,null);
INSERT INTO teden VALUES (2,2,null,null);
INSERT INTO teden VALUES (3,3,null,null);
INSERT INTO teden VALUES (4,4,null,null);

DROP TABLE IF EXISTS dan;
CREATE TABLE dan(
  danID INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  tedenID INT NOT NULL,
  date DATE,
  opravljen BOOL,
  FOREIGN KEY (tedenID) REFERENCES teden(tedenID)
);

INSERT INTO dan VALUES (1,1,null,FALSE);
INSERT INTO dan VALUES (2,1,null,FALSE);
INSERT INTO dan VALUES (3,1,null,FALSE);
INSERT INTO dan VALUES (4,1,null,FALSE);
INSERT INTO dan VALUES (5,1,null,FALSE);
INSERT INTO dan VALUES (6,1,null,FALSE);
INSERT INTO dan VALUES (7,1,null,FALSE);

INSERT INTO dan VALUES (8,2,null,FALSE);
INSERT INTO dan VALUES (9,2,null,FALSE);
INSERT INTO dan VALUES (10,2,null,FALSE);
INSERT INTO dan VALUES (11,2,null,FALSE);
INSERT INTO dan VALUES (12,2,null,FALSE);
INSERT INTO dan VALUES (13,2,null,FALSE);
INSERT INTO dan VALUES (14,2,null,FALSE);

DROP TABLE IF EXISTS danOpravilo;
CREATE TABLE danOpravilo(
  danID INT NOT NULL,
  opraviloID INT NOT NULL,
  opravljeno BOOL DEFAULT FALSE,
  PRIMARY KEY (danID,opraviloID),
  FOREIGN KEY (danID) REFERENCES dan(danID),
  FOREIGN KEY (opraviloID) REFERENCES opravilo(opraviloID)
);


-- SELECT * FROM opravilo;
