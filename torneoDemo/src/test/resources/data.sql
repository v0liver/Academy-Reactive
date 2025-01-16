-- INSERT per la tabella squadra
INSERT INTO squadra (nome, colori_sociali) VALUES
('Juventus', 'Bianco e Nero'),
('Inter', 'Nero e Azzurro'),
('Milan', 'Rosso e Nero'),
('Napoli', 'Azzurro'),
('Roma', 'Giallo e Rosso');

-- INSERT per la tabella tifoseria
INSERT INTO tifoseria (nome_tifoseria, id_squadra) VALUES
('Curva Sud Juventus', 1),
('Curva Nord Inter', 2),
('Fossa dei Leoni', 3),
('Curva B Napoli', 4),
('Fedayn Roma', 5);

-- INSERT per la tabella giocatore
INSERT INTO giocatore (nome_cognome, numero_ammonizioni, id_squadra) VALUES
('Leonardo Bonucci', 2, 1),
('Lautaro Martinez', 1, 2),
('Olivier Giroud', 0, 3),
('Victor Osimhen', 3, 4),
('Paulo Dybala', 1, 5);

-- INSERT per la tabella torneo
INSERT INTO torneo (nome_torneo) VALUES
('Serie A'),
('Coppa Italia'),
('Champions League'),
('Europa League'),
('Supercoppa Italiana');

-- INSERT per la tabella squadra_torneo
INSERT INTO squadra_torneo (id_squadra, id_torneo) VALUES
(1, 1), -- Juventus partecipa a Serie A
(1, 2), -- Juventus partecipa a Coppa Italia
(2, 1), -- Inter partecipa a Serie A
(2, 3), -- Inter partecipa a Champions League
(3, 1), -- Milan partecipa a Serie A
(4, 1), -- Napoli partecipa a Serie A
(5, 1); -- Roma partecipa a Serie A
