package com.example.infoevenement.dto;

import com.example.infoevenement.dao.Category;
import com.example.infoevenement.dao.Lieux;
import com.example.infoevenement.dao.Periode;
import lombok.Data;

@Data
public class EvenementDto {
    private String libelle;
    private String description;
    private Lieux lieux;
    private Category category;
    private Periode periode;
}
