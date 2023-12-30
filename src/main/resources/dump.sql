PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS drzava (id INTEGER PRIMARY KEY, naziv TEXT, glavni_grad INTEGER REFERENCES grad (id));
INSERT INTO drzava (id, naziv, glavni_grad) VALUES (1, 'Francuska', 1);
INSERT INTO drzava (id, naziv, glavni_grad) VALUES (2, 'Velika Britanija', 2);
INSERT INTO drzava (id, naziv, glavni_grad) VALUES (3, 'Austrija', 4);

CREATE TABLE IF NOT EXISTS grad (id INTEGER PRIMARY KEY, naziv TEXT, broj_stanovnika INTEGER, drzava INTEGER REFERENCES drzava (id));
INSERT INTO grad (id, naziv, broj_stanovnika, drzava) VALUES (1, 'Pariz', 2193031, 1);
INSERT INTO grad (id, naziv, broj_stanovnika, drzava) VALUES (2, 'London', 8538700, 2);
INSERT INTO grad (id, naziv, broj_stanovnika, drzava) VALUES (3, 'Manchester', 520000, 2);
INSERT INTO grad (id, naziv, broj_stanovnika, drzava) VALUES (4, 'Bec', 1800000, 3);
INSERT INTO grad (id, naziv, broj_stanovnika, drzava) VALUES (5, 'Graz', 280800, 3);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
