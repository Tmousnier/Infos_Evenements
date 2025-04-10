package com.example.infoevenement.dto;

import com.example.infoevenement.dao.Lieux;
import lombok.Data;

@Data
public class EvenementDto {
    private String libelle;
    private String description;
    private Lieux lieux;
}
