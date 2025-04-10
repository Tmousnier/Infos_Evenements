CREATE TABLE IF NOT EXISTS Evenement  (
                           id VARCHAR(100) PRIMARY KEY,
                           libelle VARCHAR(255) NOT NULL,
                           description TEXT,
                           category_id VARCHAR(100),
                           periode_id VARCHAR(100),
                           lieux_id VARCHAR(100),

    CONSTRAINT fk_evenement_categorie FOREIGN KEY (category_id)
    REFERENCES category(id),

    CONSTRAINT fk_evenement_periode FOREIGN KEY (periode_id)
    REFERENCES periode(id),

    CONSTRAINT fk_evenement_lieu FOREIGN KEY (lieux_id)
    REFERENCES lieux(id)


);


