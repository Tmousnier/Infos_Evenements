package com.example.infoevenement.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table (name = "category")
public class Category {
    @Id
    @UuidGenerator

    private String id;
    private String libelle;
}
