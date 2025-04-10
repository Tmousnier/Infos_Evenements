package com.example.infoevenement.service;

import com.example.infoevenement.dao.Periode;
import com.example.infoevenement.dto.PeriodeInput;
import com.example.infoevenement.repository.PeriodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PeriodeService {

    private final PeriodeRepository periodeRepository;

    @Autowired
    public PeriodeService(PeriodeRepository periodeRepository) {
        this.periodeRepository = periodeRepository;
    }

    public List<Periode> getAll() {
        List<Periode> periodes = periodeRepository.findAll();
        periodes.forEach(p -> p.setStatut(calculerStatut(p)));
        return periodes;
    }


    public Periode getById(String id) {
        Periode p = periodeRepository.findById(id).orElse(null);
        if (p != null) {
            p.setStatut(calculerStatut(p));
        }
        return p;
    }


    public Periode createPeriode(PeriodeInput periodeInput) {
        // Créer la nouvelle période
        Periode newPeriode = Periode.builder()
                .dateDebut(periodeInput.date_debut())
                .dateFin(periodeInput.date_fin())
                .build();

        // Calculer le statut avant de sauvegarder la période
        String statut = calculerStatut(newPeriode);
        newPeriode.setStatut(statut);

        // Sauvegarder la période dans la base de données
        return periodeRepository.save(newPeriode);
    }


    public void deletePeriode(String id) {
        Periode periode = periodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid periode ID"));
        periodeRepository.delete(periode);
    }

    public Periode updatePeriode(String id, PeriodeInput periodeInput) {
        // Récupérer la période existante
        Periode existingPeriode = periodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid periode ID"));

        // Mettre à jour les dates de début et de fin
        existingPeriode.setDateDebut(periodeInput.date_debut());
        existingPeriode.setDateFin(periodeInput.date_fin());

        // Calculer le statut mis à jour
        existingPeriode.setStatut(calculerStatut(existingPeriode));

        // Sauvegarder la période mise à jour
        return periodeRepository.save(existingPeriode);
    }



    private String calculerStatut(Periode periode) {
        Instant now = Instant.now();
        if (now.isBefore(periode.getDateDebut())) {
            return "À Venir";
        } else if (!now.isAfter(periode.getDateFin())) {
            return "En Cours";
        } else {
            return "Terminé";
        }
    }

}