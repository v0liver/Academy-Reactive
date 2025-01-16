drop table if exists squadra cascade;
drop table if exists tifoseria cascade;
drop table if exists giocatore cascade;
drop table if exists torneo cascade;
drop table if exists squadra_torneo cascade;

create table squadra(
    id int auto_increment primary key,
    nome varchar(255) unique,
    colori_sociali varchar(255) not null
);

create table tifoseria(
    id int auto_increment primary key,
    nome_tifoseria varchar(255) unique,
    id_squadra integer not null
);

create table giocatore(
    id int auto_increment primary key,
    nome_cognome varchar(255) unique,
    numero_ammonizioni integer default 0,
    id_squadra integer not null
);

create table torneo(
    id int auto_increment primary key,
    nome_torneo varchar(255) unique
);

create table squadra_torneo(
    id_squadra integer not null,
    id_torneo integer not null,
    primary key(id_squadra, id_torneo)
);

alter table tifoseria add constraint fk_squadra_tifoseria foreign key (id_squadra) references squadra(id);
alter table giocatore add constraint fk_squadra_giocatore foreign key (id_squadra) references squadra(id);
alter table squadra_torneo add constraint fk_squadra foreign key (id_squadra) references squadra(id);
alter table squadra_torneo add constraint fk_torneo foreign key (id_torneo) references torneo(id);
