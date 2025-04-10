package com.example.infoevenement.dao;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Evenement")
public class Evenement {
    @Id
    @UuidGenerator
    private String id;
    private String libelle;
    private String description;
    @ManyToOne
    @JoinColumn(name = "lieux_id", referencedColumnName = "id")
    private Lieux lieux;
}
