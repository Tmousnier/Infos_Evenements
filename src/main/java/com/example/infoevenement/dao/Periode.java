package com.example.infoevenement.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "periode")
public class Periode {
    @Id
    @UuidGenerator
    private String id;
    @Column(name = "date_debut")
    private Instant dateDebut;
    @Column(name = "date_fin")
    private Instant dateFin;
    private String statut;
}