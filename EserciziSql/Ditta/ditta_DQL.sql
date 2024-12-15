--1)	Estrarre il nome e il cognome dei clienti nati nel 1982
select
	c.nome,
	c.cognome
from
	clienti c
where
	extract (year
from
	c.data)= 1982;

--2)	Estrarre una colonna di nome “Denominazione” contenente il nome, seguito da un carattere “-“, seguito dal cognome, 
--per i soli clienti residenti nella regione Lombardia
select
	concat(c.nome,
	'-',
	c.cognome)
from
	clienti c
where
	c.regioneResidenza = 'Lombardia';

--3)	Qual è il numero di fatture con iva al 22%?
select count(*) NumeroFattureAl22 from fatture where iva=22;

--4)	Riportare il numero di fatture e la somma dei relativi importi divisi per anno di fatturazione.
select
	extract (year
from
	f.dataFattura) Anno ,
	sum(importo),
	count(*) numeroFatturePerAnno
from
	fatture f
group by
	extract (year
from
	f.dataFattura);

--5)	Estrarre i prodotti attivati nel 2017 e che sono in produzione oppure in commercio
	select
	p.idProdotto,
	p.descrizione
from
	prodotti p
where
	extract (year
from
	p.dataAttivazione) = '2017'
	and (p.inProduzione = true
		or p.inCommercio = true);
	
--	6)	Considerando soltanto le fatture con iva al 22 per cento, qual è il numero di fatture per ogni anno?
	select
	extract (year
from
	f.dataFattura) Anno,
	count(*) FattureAl22
from
	fatture f
where
	f.iva = 22
group by
	anno;

--7)	In quali anni sono state registrate più di 2 fatture con tipologia ‘A’?
select
	extract (year
from
	f.dataFattura) AnnoFattura_Tipologia_A
	from fatture f
	where f.tipologia='A'
	group by AnnoFattura_Tipologia_A
	having count(*)>=2;

--8)	Riportare l’elenco delle fatture (numero, importo, iva e data) con in aggiunta il nome del fornitore
	select
	f.numeroFattura,
	f.importo,
	f.iva,
	f.dataFattura,
	fr.denominazione
from
	fatture f
join fornitori fr on
	f.numerofornitore = fr.numerofornitore;

--9)	Estrarre il totale degli importi delle fatture divisi per residenza dei clienti
select
	c.regioneResidenza,
	sum(f.importo) SommaImporti
from
	clienti c
join fatture f on
	c.numeroCliente = f.idcliente
group by
	c.regioneResidenza;

--10)	Estrarre il numero dei clienti nati nel 1980 che hanno almeno una fattura superiore a 50 euro
select
	count(*) numero_di_clienti_1980_con_fattura_superiore50
from
	clienti c
join fatture f on
	c.numeroCliente = f.idcliente
where
	extract (year
from
	c.dataNascita)=1980 and f.importo>50;

2
8
9
15
17
