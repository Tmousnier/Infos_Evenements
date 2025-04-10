package com.example.infoevenement.dto;

import com.example.infoevenement.dao.Evenement;
import com.example.infoevenement.dao.Lieux;


public record EvenementInput (
     String libelle,
     String description,
     Lieux lieux
) {
    public EvenementInput {
        if (lieux != null) {
            throw new IllegalArgumentException("Invalid lieux");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Invalid description");
        }
        if (libelle == null || libelle.isBlank()) {
            throw new IllegalArgumentException("Invalid libelle");
        }
    }
}




