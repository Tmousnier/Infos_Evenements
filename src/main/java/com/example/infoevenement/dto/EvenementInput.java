package com.example.infoevenement.dto;

import com.example.infoevenement.dao.Category;
import com.example.infoevenement.dao.Lieux;
import com.example.infoevenement.dao.Periode;

public record EvenementInput (
     String libelle,
     String description,
     Lieux lieux,
     Category category,
     Periode periode
) {
    public EvenementInput {
        if (libelle == null || libelle.isBlank()) {
            throw new IllegalArgumentException("Invalid libelle");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Invalid description");
        }
        if (lieux == null) {
            throw new IllegalArgumentException("Invalid lieux");
        }
        if (category == null) {
            throw new IllegalArgumentException("Invalid category");
        }
        if (periode == null) {
            throw new IllegalArgumentException("Invalid periode");
        }
    }
}