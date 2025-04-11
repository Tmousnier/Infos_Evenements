package com.example.infoevenement.controller;

import com.example.infoevenement.dao.*;
import com.example.infoevenement.dto.EvenementDto;
import com.example.infoevenement.dto.EvenementInput;
import com.example.infoevenement.mapper.EvenementMapper;
import com.example.infoevenement.service.EvenementService;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("api/v1/evenement")
public class EvenementController {

    private final EvenementService evenementService;
    private final EvenementMapper evenementMapper;

    @Autowired
    public EvenementController(EvenementService evenementService, EvenementMapper evenementMapper) {
        this.evenementService = evenementService;
        this.evenementMapper = evenementMapper;
    }

    @GetMapping("/libelle/{keyword}")
    public ResponseEntity<Page<EvenementDto>> findByLibleContaining(@PathVariable String keyword, Pageable pageable) {
        Page<EvenementDto> annonces = evenementService.searchByLibelle(keyword, pageable);
        return ResponseEntity.ok(annonces);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Evenement>> getEvenements(
            @RequestParam(value = "libelle", required = false) String libelle,
            @RequestParam(value = "lieux", required = false) String lieux,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "dateDebut", required = false) Instant dateDebut,
            @RequestParam(value = "dateFin", required = false) Instant dateFin,
            @RequestParam(value = "statut", required = false) String statut,
            Pageable pageable) {

        QEvenement qEvenement = QEvenement.evenement;
        BooleanExpression predicate = qEvenement.isNotNull();
        if (libelle != null) {
            predicate = predicate.and(qEvenement.libelle.containsIgnoreCase(libelle));
        }
        if (lieux != null) {
            predicate = predicate.and(qEvenement.lieux.id.eq(lieux));
        }
        if (category != null) {
            predicate = predicate.and(qEvenement.category.libelle.containsIgnoreCase(category));
        }
        if (dateDebut != null) {
            predicate = predicate.and(qEvenement.periode.dateDebut.eq(dateDebut));
        }
        if (dateFin != null) {
            predicate = predicate.and(qEvenement.periode.dateFin.eq(dateFin));
        }
        if (statut != null) {
            predicate = predicate.and(qEvenement.periode.statut.containsIgnoreCase(statut));
        }

        Page<Evenement> evenements = evenementService.getAll(pageable, predicate);
        return ResponseEntity.ok(evenements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvenementDto> getById(@PathVariable String id) {
        EvenementDto evenement = evenementService.getById(id);
        if (evenement == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evenement);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<EvenementDto> createEvenement(@RequestParam ("libelle") String libelle,
                                                        @RequestParam ("description") String description,
                                                        @RequestParam ("lieux.id") String lieuxId,
                                                        @RequestParam ("category.id") String categoryId,
                                                        @RequestParam ("periode.id") String periodeId) {
        Lieux lieux = new Lieux();
        lieux.setId(lieuxId);
        Category category = new Category();
        category.setId(categoryId);
        Periode periode = new Periode();
        periode.setId(periodeId);
        EvenementInput evenementInput = new EvenementInput(libelle, description, lieux, category, periode);
        EvenementDto evenementDto = evenementService.createEvenement(evenementInput);
        return ResponseEntity.status(201).body(evenementDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvenementDto> updateEvenement(@PathVariable String id, @RequestBody EvenementInput evenementInput) {
        try {
            EvenementDto updatedEvenement = evenementService.updateEvenement(id, evenementInput);
            return ResponseEntity.ok(updatedEvenement);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        try {
            evenementService.deleteEvenement(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}