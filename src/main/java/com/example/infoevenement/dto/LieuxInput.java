package com.example.infoevenement.dto;

public record LieuxInput(
        String ville
) {
    public LieuxInput {
        if (ville == null || ville.isBlank()) {
            throw new IllegalArgumentException("Ville name cannot be null or blank");
        }
    }
}
