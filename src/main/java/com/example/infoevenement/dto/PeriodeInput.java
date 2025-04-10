package com.example.infoevenement.dto;

import java.time.Instant;


public record PeriodeInput(
        Instant date_debut,
        Instant date_fin
) {
    public PeriodeInput {
        if (date_debut == null || date_fin == null) {
            throw new IllegalArgumentException("Date de début, date de fin ne peuvent pas être nuls");
        }
        if (date_debut.isAfter(date_fin)) {
            throw new IllegalArgumentException("La date de début doit être antérieure à la date de fin");
        }
    }
}