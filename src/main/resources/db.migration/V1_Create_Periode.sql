CREATE TABLE IF NOT EXISTS Periode (
    id VARCHAR(100) PRIMARY KEY,
    date_debut TIMESTAMP not null ,
    date_fin TIMESTAMP not null ,
    statut VARCHAR(50)
);

