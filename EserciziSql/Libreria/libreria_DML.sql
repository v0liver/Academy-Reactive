--Creare gli script per popolare il database con valori di fantasia (con insert).

insert into librerie (nome,citta) values ('Mistral','Torino');
insert into librerie (nome,citta) values ('Arcadia','Torino');
insert into librerie (nome,citta) values ('Polaris','Roma');
insert into librerie (nome,citta) values ('Biblioteka','Palermo');
insert into librerie (nome,citta) values ('Nova','Palermo');

--INSERT INTO generi (NOME_GENERE) VALUES
--('Storico'),
--('Politico'),
--('Drammatico'),
--('Fantasy'),
--('Thriller');

INSERT INTO generi (NOME_GENERE) VALUES ('Storico');
INSERT INTO generi (NOME_GENERE) VALUES ('Politico');
INSERT INTO generi (NOME_GENERE) VALUES ('Drammatico');
INSERT INTO generi (NOME_GENERE) VALUES ('Fantasy');
INSERT INTO generi (NOME_GENERE) VALUES ('Thriller');

INSERT INTO autori (nome, cognome) VALUES ('Mario', 'Rossi');
INSERT INTO autori (nome, cognome) VALUES ('Giulia', 'Bianchi');
INSERT INTO autori (nome, cognome) VALUES ('Luca', 'Verdi');
INSERT INTO autori (nome, cognome) VALUES ('Anna', 'Neri');
INSERT INTO autori (nome, cognome) VALUES ('Paolo', 'Gialli');
INSERT INTO autori (nome, cognome) VALUES ('Vito', 'Oliveri');

INSERT INTO caseeditrici (ID_casaEditrice, nome_casaeditrice) VALUES (101, 'Mondadori');
INSERT INTO caseeditrici (ID_casaEditrice, nome_casaeditrice) VALUES (102, 'Feltrinelli');
INSERT INTO caseeditrici (ID_casaEditrice, nome_casaeditrice) VALUES (103, 'Rizzoli');
INSERT INTO caseeditrici (ID_casaEditrice, nome_casaeditrice) VALUES (104, 'Einaudi');
INSERT INTO caseeditrici (ID_casaEditrice, nome_casaeditrice) VALUES (105, 'Garzanti');
INSERT INTO caseeditrici (ID_casaEditrice, nome_casaeditrice) VALUES (107, 'Casa Editrice senza libri');

insert into libri (titolo,genere,id_autore,"data",id_casaeditrice) values ('Il Nome della Rosa', 'Storico', 1, '1980-01-01', 101);
insert into libri (titolo,genere,id_autore,"data",id_casaeditrice) values ('1984', 'Politico', 2, '1949-06-08', 102);
insert into libri (titolo,genere,id_autore,"data",id_casaeditrice) values ('Il Grande Gatsby', 'Drammatico', 3, '1925-04-10', 103);
insert into libri (titolo,genere,id_autore,"data",id_casaeditrice) values ('Harry Potter e la Pietra Filosofale', 'Fantasy', 4, '1997-06-26', 104);
insert into libri (titolo,genere,id_autore,"data",id_casaeditrice) values ('Il Codice Da Vinci', 'Thriller', 5, '2003-03-18', 105);
insert into libri (titolo,genere,id_autore,"data",id_casaeditrice) values ('Libro Senza Casa Editrice', 'Thriller', 1, '2024-12-15', 106);
insert into libri (titolo,genere,id_autore,"data",id_casaeditrice) values ('Libro Senza Casa Editrice', 'Thriller', 2, '2024-12-16', 109);
insert into libri (titolo,genere,id_autore,"data",id_casaeditrice) values ('Libro Senza Casa Editrice', 'Thriller', 3, '2024-12-18', 110);
insert into libri (titolo,genere,id_autore,"data",id_casaeditrice) values ('Libro Senza Casa Editrice', 'Thriller', 3, '2024-12-20', 110);

