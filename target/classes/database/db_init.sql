DROP TABLE IF EXISTS user;
CREATE TABLE user (
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
INSERT INTO opravilo VALUES (0,"KUHINJA PULT","Obrisi pult.");
INSERT INTO opravilo VALUES (0,"KUHINJA LIJAK","Obrisi lijak.");
INSERT INTO opravilo VALUES (0,"KUHINJA TLA","Pometi tla.");
INSERT INTO opravilo VALUES (0,"KUHINJA SMETI","Odnesi smeti.");

INSERT INTO opravilo VALUES (0,"DNEVNA MIZA","Pobrisi mizo.");
INSERT INTO opravilo VALUES (0,"DNEVNA TLA","Pometi tla..");

INSERT INTO opravilo VALUES (0,"WC LIJAK","Obrisi lijak.");
INSERT INTO opravilo VALUES (0,"WC SKOLJKA","Obrisi skoljko.");
INSERT INTO opravilo VALUES (0,"WC TLA","Pobrisi tla.");

INSERT INTO opravilo VALUES (0,"HODNIK", "Uredi hodnik.");
INSERT INTO opravilo VALUES (0,"BALKON PEPELNIKI", "Sprazni pepelnike.");
INSERT INTO opravilo VALUES (0,"BALKON TLA", "Pometi tla.");

-- SELECT * FROM opravilo;
