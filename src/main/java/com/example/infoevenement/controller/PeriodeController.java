package com.example.infoevenement.controller;


import com.example.infoevenement.dao.Periode;
import com.example.infoevenement.dto.PeriodeInput;
import com.example.infoevenement.service.PeriodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/periodes")
public class PeriodeController {

    private final PeriodeService periodeService;

    @Autowired
    public PeriodeController(PeriodeService periodeService) { this.periodeService = periodeService; }

    @GetMapping
    public List<Periode> getAll() { return periodeService.getAll(); }

    @GetMapping("/{id}")
    public Periode getById(@PathVariable String id) { return periodeService.getById(id); }

    @PostMapping
    public Periode createperiode(@RequestBody PeriodeInput periodeInput) {
        return periodeService.createPeriode(periodeInput);
    }

    @PutMapping("/{id}")
    public Periode updateperiode(@PathVariable String id, @RequestBody PeriodeInput periodeInput) {
        Periode updatedperiode = periodeService.updatePeriode(id, periodeInput);
        return ResponseEntity.ok(updatedperiode).getBody();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        periodeService.deletePeriode(id);
    }


}
