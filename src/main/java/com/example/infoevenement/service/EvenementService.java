package com.example.infoevenement.service;

import com.example.infoevenement.dao.Category;
import com.example.infoevenement.dao.Periode;
import com.example.infoevenement.mapper.EvenementMapper;
import com.example.infoevenement.dao.Evenement;
import com.example.infoevenement.dao.Lieux;
import com.example.infoevenement.dto.EvenementDto;
import com.example.infoevenement.dto.EvenementInput;
import com.example.infoevenement.repository.CategoryRepository;
import com.example.infoevenement.repository.EvenementRepository;
import com.example.infoevenement.repository.LieuxRepository;
import com.example.infoevenement.repository.PeriodeRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EvenementService {

    private final EvenementRepository evenementRepository;
    private final EvenementMapper evenementMapper;
    private final LieuxRepository lieuxRepository;
    private final CategoryRepository categoryRepository;
    private final PeriodeRepository periodeRepository;

    @Autowired
    public EvenementService(EvenementRepository evenementRepository, EvenementMapper evenementMapper, LieuxRepository lieuxRepository, CategoryRepository categoryRepository, PeriodeRepository periodeRepository) {
        this.evenementRepository = evenementRepository;
        this.evenementMapper = evenementMapper;
        this.lieuxRepository = lieuxRepository;
        this.categoryRepository = categoryRepository;
        this.periodeRepository = periodeRepository;
    }

    public Page<Evenement> getAll(Pageable pageable, Predicate predicate) {
        return evenementRepository.findAll(predicate, pageable);
    }
    public Page<EvenementDto> searchByLibelle(String keyword, Pageable pageable) {
        return evenementRepository.searchByLibelle(keyword, pageable)
                .map(evenementMapper::toDto);
    }
    public EvenementDto getById(String id) {
        return evenementMapper.toDto(evenementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("l'id de l'annonce est introuvable")));
    }
    public EvenementDto createEvenement(EvenementInput evenementInput) {
        Lieux lieux = lieuxRepository.findById(evenementInput.lieux().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        Category category = categoryRepository.findById(evenementInput.category().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        Periode periode = periodeRepository.findById(evenementInput.periode().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        Evenement evenement = evenementMapper.toEntity(evenementInput);
        evenement.setLieux(lieux);
        evenement.setCategory(category);
        evenement.setPeriode(periode);

        Evenement savedEvenement = evenementRepository.save(evenement);
        return evenementMapper.toDto(savedEvenement);
    }
    public void deleteEvenement(String id) {
        Evenement evenement = evenementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid evenement ID"));
        evenementRepository.delete(evenement);
    }
    public EvenementDto updateEvenement(String id, EvenementInput evenementInput) {
        Evenement evenement = evenementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Événement non trouvé avec l'ID : " + id));
        Lieux lieux = lieuxRepository.findById(evenementInput.lieux().getId())
                .orElseThrow(() -> new IllegalArgumentException("Lieu non trouvé avec l'ID : " + evenementInput.lieux().getId()));
        Category category = categoryRepository.findById(evenementInput.category().getId())
                .orElseThrow(() -> new IllegalArgumentException("Categorie non trouvé avec l'ID : " + evenementInput.lieux().getId()));
        Periode periode = periodeRepository.findById(evenementInput.periode().getId())
                .orElseThrow(() -> new IllegalArgumentException("Periode non trouvé avec l'ID : " + evenementInput.lieux().getId()));
        evenement.setLibelle(evenementInput.libelle());
        evenement.setDescription(evenementInput.description());
        evenement.setLieux(lieux);
        evenement.setCategory(category);
        evenement.setPeriode(periode);
        Evenement updatedEvenement = evenementRepository.save(evenement);
        return evenementMapper.toDto(updatedEvenement);
    }
}