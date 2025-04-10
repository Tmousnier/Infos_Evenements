package com.example.infoevenement.controller;

import com.example.infoevenement.dao.Lieux;
import com.example.infoevenement.dto.LieuxInput;
import com.example.infoevenement.service.LieuxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lieux")
public class LieuxController {

    private final LieuxService lieuxService;

    @Autowired
    public LieuxController(LieuxService lieuxService) { this.lieuxService = lieuxService; }

    @GetMapping
    public List<Lieux> getAll() { return lieuxService.getAll(); }

    @GetMapping("/{id}")
    public Lieux getById(@PathVariable String id) { return lieuxService.getById(id); }

    @PostMapping
    public Lieux createLieux(@RequestBody LieuxInput lieuxInput) {
        return lieuxService.createLieux(lieuxInput);
    }

    @PutMapping("/{id}")
    public Lieux updateLieux(@PathVariable String id, @RequestBody LieuxInput lieuxInput) {
        Lieux updatedLieux = lieuxService.updateLieux(id, lieuxInput);
        return ResponseEntity.ok(updatedLieux).getBody();
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        lieuxService.deleteLieux(id);
    }
}
