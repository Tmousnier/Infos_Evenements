package com.example.infoevenement.controller;

import com.example.infoevenement.dao.Evenement;
import com.example.infoevenement.dao.Lieux;
import com.example.infoevenement.dao.QEvenement;
import com.example.infoevenement.dto.EvenementDto;
import com.example.infoevenement.dto.EvenementInput;
import com.example.infoevenement.service.EvenementService;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/evenement")
public class EvenementController {

    private final EvenementService evenementService;

    @Autowired
    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Evenement>> getEvenements(
            @RequestParam(value = "libelle", required = false) String libelle,
            @RequestParam(value = "lieux", required = false) String lieux,
            Pageable pageable) {

        EvenementPredicateBuilder predicateBuilder = new EvenementPredicateBuilder();
        BooleanExpression predicate = predicateBuilder.build(libelle, lieux);

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
                                                        @RequestParam ("lieux.id") String lieuxId) {
        Lieux lieux = new Lieux();
        lieux.setId(lieuxId);
        EvenementInput evenementInput = new EvenementInput(libelle, description, lieux);
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
