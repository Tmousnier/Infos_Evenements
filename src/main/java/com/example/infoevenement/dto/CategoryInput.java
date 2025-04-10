package com.example.infoevenement.dto;

public record CategoryInput(String libelle){
    public CategoryInput {
        if (libelle == null || libelle.isBlank()) {
            throw new IllegalArgumentException("Libelle cannot be null or blank");
        }
    }
}
