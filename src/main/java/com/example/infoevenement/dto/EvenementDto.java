package com.example.infoevenement.dto;

import com.example.infoevenement.dao.Lieux;
import lombok.Data;

@Data
public class EvenementDto {
    String libelle;
    String description;
    Lieux lieux;
}
