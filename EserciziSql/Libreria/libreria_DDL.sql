drop view if exists LIBRERIE_LIBRI_TORINO; 
drop table if exists LIBRI_LIBRERIE;
drop table if exists LIBRI;
drop table if exists LIBRERIE;
drop table if exists CASEEDITRICI;
drop table if exists AUTORI;
drop table if exists GENERI;


--La tabella librerie ha i campi nome e citta entrambi chiave primaria e deve essere definita con una sola istruzione SQL.
create table LIBRERIE (
	NOME VARCHAR(100),
	CITTA VARCHAR(100),
	primary key(NOME,
	CITTA)
	);

--La tabella libri ha un id autoincrementale PK, un titolo ed i riferimenti al genere ed all’autore (entrambi not null). Inoltre, sono presenti i campi, senza vincoli,: 
--date per memorizzare la data di pubblicazione del libro 
--int campo idCasaEditrice.
create table LIBRI (
	ID_LIBRO SERIAL primary key,
	TITOLO VARCHAR(100),
	GENERE VARCHAR(100) not null,
	ID_AUTORE INT not null,
	data DATE,
	ID_CASAEDITRICE INT
	);

--La tabella caseEditrici con le colonne id e casa.
create table CASEEDITRICI(
	ID_casaEditrice INT,
	nome_casaeditrice VARCHAR(100)
	);

--Una tabella per relazionare i libri e le librerie con tutti i campi nella PK e 2 FK verso libri e libreria. Definire il tutto con una sola istruzione SQL.
create table LIBRI_LIBRERIE(
	LIBRERIE_NOME VARCHAR(100),
	LIBRERIE_CITTA VARCHAR(100),
	ID_LIBRO INT,
	foreign key(ID_LIBRO) references LIBRI(ID_LIBRO),
	FOREIGN KEY (LIBRERIE_NOME,LIBRERIE_CITTA) REFERENCES librerie(nome,citta)
);

--Una tabella autori con 2 colonne nome e cognome dell’autore. La PK crearla contestualmente alla creazione della tabella. 
--Aggiungere una Alter per definire una chiave univoca su nome e cognome.
create table AUTORI(
	ID_AUTORE SERIAL primary key,
	NOME VARCHAR(100),
	COGNOME VARCHAR(100)
	
);

--Una tabella generi con un campo testo di nome genere.
create table GENERI(
	NOME_GENERE VARCHAR(100) unique
);

--Definire in coda allo script DDL l’alter della tabella libri per memorizzare le FK verso generi ed autori.
alter table LIBRI add constraint FK_LIBRI_GENERI foreign key(GENERE) references GENERI(NOME_GENERE);

alter table LIBRI add constraint FK_LIBRI_AUTORI foreign key(ID_AUTORE) references AUTORI(ID_AUTORE);

alter table AUTORI add constraint AUTORI_NOME_COGNOME unique (NOME,COGNOME);

--Creare la vista librerie_libri_torino che resituisce nomelibreria e tutti i campi di libri per le librerie di Torino
create view LIBRERIE_LIBRI_TORINO 
	as select LIBRERIE_NOME,l.*  
	from libri_librerie ll,libri l 
	where ll.id_libro=l.id_libro 
	and ll.librerie_citta ='Torino';



--oppure
--create view librerie_libri_torino 
--	as select LIBRERIE_NOME  
--	from libri_librerie ll
--	join libri l on ll.id_libro=l.id_libro 
--	where ll.librerie_citta ='Torino';
