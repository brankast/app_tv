DROP SCHEMA IF EXISTS programskaSema;
CREATE SCHEMA programskaSema DEFAULT CHARACTER SET utf8;
USE programskaSema;

CREATE TABLE emisija (
	id INT AUTO_INCREMENT,
	naziv VARCHAR (35) NOT NULL,
	vremeTrajanjaMin INT NOT NULL,
	rejting INT,

	PRIMARY KEY(id)
);

CREATE TABLE programskaSema (
	id INT AUTO_INCREMENT,
	datum DATE UNIQUE NOT NULL,

	PRIMARY KEY(id)
);

CREATE TABLE termin (
	semaID INT, 
	redniBroj INT,
    emisijaID INT, 
	pocetak TIME NOT NULL, 

	PRIMARY KEY (semaID, redniBroj, emisijaID), 
	FOREIGN KEY (semaID) REFERENCES programskaSema(id) ON DELETE RESTRICT, 
	FOREIGN KEY (emisijaID) REFERENCES emisija(id) ON DELETE RESTRICT
);

INSERT INTO emisija (naziv, vremeTrajanjaMin, rejting) VALUES ('Jutarnji program', 240, 0);
INSERT INTO emisija (naziv, vremeTrajanjaMin, rejting) VALUES ('Serija1', 60, 0);
INSERT INTO emisija (naziv, vremeTrajanjaMin, rejting) VALUES ('Emisija1', 60, 0);
INSERT INTO emisija (naziv, vremeTrajanjaMin, rejting) VALUES ('Dnevnik', 45, 0);

INSERT INTO programskaSema (datum) VALUES ( '2019-12-14');
INSERT INTO programskaSema (datum) VALUES ( '2019-12-15');
INSERT INTO programskaSema (datum) VALUES ( '2019-12-16');
INSERT INTO programskaSema (datum) VALUES ( '2019-12-17');

INSERT INTO termin (semaID, redniBroj, emisijaID, pocetak) VALUES (1, 1, 1, '06:00');
INSERT INTO termin (semaID, redniBroj, emisijaID, pocetak) VALUES (1, 2, 2, '10:00');
INSERT INTO termin (semaID, redniBroj, emisijaID, pocetak) VALUES (1, 3, 3, '11:00');
INSERT INTO termin (semaID, redniBroj, emisijaID, pocetak) VALUES (1, 4, 4, '13:00');
INSERT INTO termin (semaID, redniBroj, emisijaID, pocetak) VALUES (1, 5, 2, '13:45');
