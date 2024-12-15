--1.	Recuperare tutte le colonne di libri ed autori mettendo in join le due tabelle con la sintassi senza la parola Join
select
	*
from
	libri l,
	autori a
where
	l.id_autore = a.id_autore;

--2.	Recuperare le colonne titolo, genere ed nome dell’autore con la sintassi inner join su tutte le relazioni
select
	l.titolo,
	l.genere,
	a.nome
from
	libri l
join autori a on
	l.id_autore = a.id_autore;

--3.	Recuperare gli autori ed i libri riportando le colonne dei libri vuote se l’autore non ha scritto libri (con l’istruzione left outer join)
select
	*
from
	autori a
left join libri l on
	a.id_autore = l.id_autore;

--4.	Recuperare gli autori ed i libri riportando le colonne dei libri vuote se l’autore non ha scritto libri (con l’istruzione right outer join)
select
	*
from
	libri l
right join autori a on
	l.id_autore = a.id_autore;

--5.	Recuperare i libri e le case editrici visualizzando le colonne della casa editrice come null se non presente la relazione
select
	*
from
	caseeditrici c
right join libri l on
	c.id_casaeditrice = l.id_casaeditrice;

--6.	Recuperare i libri e le case editrici visualizzando le colonne dei libri come null se non presente la relazione
select
	*
from
	caseeditrici c
left join libri l on
	l.id_casaeditrice = c.id_casaeditrice;

--7.	Estrarre il conteggio dei libri per genere ed autore riportando le colonne genere, cognome e conteggio per tutti i libri scritti dopo il ’01-01-2021’
select
	l.genere,
	a.cognome,
	count(*)
from
	libri l
join autori a on
	l.id_autore = a.id_autore
where
	l."data" > '01-01-2021'
group by
	l.genere ,
	a.cognome;

--8.	Estrarre il conteggio dei libri per genere ed autore riportando le colonne genere, cognome e conteggio per tutte le occorrenze presenti almeno due volte
select
	l.genere,
	a.cognome,
	count(*)
from
	libri l
join autori a on
	l.id_autore = a.id_autore
group by
	l.genere ,
	a.cognome
having
	count(*)>= 2;