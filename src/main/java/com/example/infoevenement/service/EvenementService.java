package com.example.infoevenement.service;
import com.example.infoevenement.Mapper.EvenementMapper;
import com.example.infoevenement.dao.Evenement;
import com.example.infoevenement.dao.Lieux;
import com.example.infoevenement.dto.EvenementDto;
import com.example.infoevenement.dto.EvenementInput;
import com.example.infoevenement.repository.EvenementRepository;
import com.example.infoevenement.repository.LieuxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class EvenementService {

    private final EvenementRepository evenementRepository;
    private final EvenementMapper evenementMapper;
    private final LieuxRepository lieuxRepository;

    @Autowired
    public EvenementService(EvenementRepository evenementRepository, EvenementMapper evenementMapper, LieuxRepository lieuxRepository) {
        this.evenementRepository = evenementRepository;
        this.evenementMapper = evenementMapper;
        this.lieuxRepository = lieuxRepository;
    }

    public List<Evenement> getAll() { return evenementRepository.findAll(); }

    public Evenement getById(String id) { return evenementRepository.findById(id).orElse(null); }

    public EvenementDto createEvenement(EvenementInput evenementInput) {
        Lieux lieux = lieuxRepository.findById(evenementInput.lieux().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        Evenement evenement = evenementMapper.toEntity(evenementInput);
        evenement.setLieux(lieux);

        Evenement savedEvenement = evenementRepository.save(evenement);
        return evenementMapper.toDto(savedEvenement);
    }

    public void deleteEvenement(String id) {
        Evenement ville = evenementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ville ID"));
        evenementRepository.delete(ville);
    }

    public EvenementDto updateEvenement(String id, EvenementInput evenementInput) {
        Evenement evenement = evenementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid annonce ID"));
        Lieux lieux = lieuxRepository.findById(evenementInput.lieux().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid ville ID"));

        evenement.setLibelle(evenementInput.libelle());
        evenement.setDescription(evenementInput.description());
        evenement.setLieux(lieux);

        Evenement updatedEvenement = evenementRepository.save(evenement);
        return evenementMapper.toDto(updatedEvenement);
    }
}
