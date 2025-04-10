package com.example.infoevenement.service;

import com.example.infoevenement.dao.Lieux;
import com.example.infoevenement.dto.LieuxInput;
import com.example.infoevenement.repository.LieuxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LieuxService {

    private final LieuxRepository lieuxRepository;

    @Autowired
    public LieuxService(LieuxRepository lieuxRepository) {
        this.lieuxRepository = lieuxRepository;
    }

    public List<Lieux> getAll() { return lieuxRepository.findAll(); }

    public Lieux getById(String id) { return lieuxRepository.findById(id).orElse(null); }

    public Lieux createLieux(LieuxInput lieuxInput) {
        Lieux newLieux = Lieux.builder().ville(lieuxInput.ville()).build();
        return lieuxRepository.save(newLieux);
    }

    public void deleteLieux(String id) {
        Lieux ville = lieuxRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ville ID"));
        lieuxRepository.delete(ville);
    }

    public Lieux updateLieux(String id, LieuxInput lieuxInput) {
        Lieux existingLieux = lieuxRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid lieux ID"));
        existingLieux.setVille(lieuxInput.ville());
        return lieuxRepository.save(existingLieux);
    }
}
