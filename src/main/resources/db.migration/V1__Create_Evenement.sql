CREATE TABLE IF NOT EXISTS Evenement  (
                           id VARCHAR(100) PRIMARY KEY,
                           libelle VARCHAR(255) NOT NULL,
                           description TEXT,
                           lieu_id VARCHAR(100)
);

/* Liaisons

CONSTRAINT fk_evenement_categorie FOREIGN KEY (categorie_id)
    REFERENCES Categorie(id),

CONSTRAINT fk_evenement_periode FOREIGN KEY (periode_id)
    REFERENCES Periode(id),

CONSTRAINT fk_evenement_lieu FOREIGN KEY (lieu_id)
    REFERENCES Lieu(id)*/
