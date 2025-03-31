package com.example.auteur_service.controller;

import com.example.auteur_service.entity.Auteur;
import com.example.auteur_service.service.AuteurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auteurs")
public class AuteurController {

    private final AuteurService service;

    public AuteurController(AuteurService service) {
        this.service = service;
    }

    @GetMapping
    public List<Auteur> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Auteur getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Auteur create(@RequestBody Auteur auteur) {
        return service.create(auteur);
    }

    @PutMapping("/{id}")
    public Auteur update(@PathVariable Long id, @RequestBody Auteur auteur) {
        return service.update(id, auteur);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
