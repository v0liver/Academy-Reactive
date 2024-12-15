drop table if exists fornitori;
drop table if exists clienti;
drop table if exists fatture;
drop table if exists prodotti;

CREATE TABLE clienti(
	numeroCliente integer PRIMARY KEY,
	nome varchar(100),
	cognome varchar(100),
	dataNascita date,
	regioneResidenza varchar(100)
);

CREATE TABLE fatture(
	numeroFattura integer PRIMARY KEY,
	tipologia varchar(100),
	importo float,
	iva integer,
	idCliente integer,
	dataFattura date,
	numeroFornitore integer
);

CREATE TABLE prodotti(
	idProdotto integer PRIMARY KEY,
	descrizione varchar(100),
	inProduzione boolean,
	inCommercio boolean,
	dataAttivazione date,
	dataDisattivazione date
);

CREATE TABLE fornitori(
	numeroFornitore integer primary Key,
	denominazione varchar(100),
	regioneResidenza varchar(100)
);

alter table fatture add constraint fk_fatture_clienti foreign key(idcliente) references clienti (numerocliente);
alter table fatture add constraint fk_fatture_fornitori foreign key(numeroFornitore) references fornitori (numeroFornitore);