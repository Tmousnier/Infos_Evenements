package com.example.infoevenement.controller;

import com.example.infoevenement.dao.Evenement;
import com.example.infoevenement.dao.Lieux;
import com.example.infoevenement.dto.EvenementDto;
import com.example.infoevenement.dto.EvenementInput;
import com.example.infoevenement.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/evenement")
public class EvenementController {

    private final EvenementService evenementService;

    @Autowired
    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @GetMapping
    public List<Evenement> getAll() {
        return evenementService.getAll();
    }

    @GetMapping("/{id}")
    public Evenement getById(@PathVariable String id) {
        return evenementService.getById(id);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<EvenementDto> createEvenement(@RequestParam ("libelle") String libelle,
                                        @RequestParam ("description") String description,
                                        @RequestParam ("lieux.id") String lieuxId) {

        try {
            Lieux lieux = new Lieux();
            lieux.setId(lieuxId);
            EvenementInput evenementInput = new EvenementInput(libelle, description, lieux);
            EvenementDto evenementDto = evenementService.createEvenement(evenementInput);
            return ResponseEntity.status(201).body(evenementDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(new EvenementDto());
        }
    }

    @PutMapping("/{id}")
    public EvenementDto updateEvenement(@PathVariable String id, @RequestBody EvenementInput evenementInput) {
        EvenementDto updatedEvenement = evenementService.updateEvenement(id, evenementInput);
        return ResponseEntity.ok(updatedEvenement).getBody();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        evenementService.deleteEvenement(id);
    }
}
