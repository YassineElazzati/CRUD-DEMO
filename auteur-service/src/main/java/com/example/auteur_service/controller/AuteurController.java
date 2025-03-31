package com.example.auteur_service.controller;

import com.example.auteur_service.entity.Auteur;
import com.example.auteur_service.service.AuteurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Cette classe est un contrôleur REST qui gère les requêtes liées à l'entité Auteur
@RestController
@RequestMapping("/api/auteurs") // Toutes les routes commencent par http://localhost:8080/api/auteurs
public class AuteurController {

    // Service contenant la logique métier
    private final AuteurService service;

    // Constructeur pour injecter le service
    public AuteurController(AuteurService service) {
        this.service = service;
    }

    /**
     * GET http://localhost:8080/api/auteurs
     * Récupère la liste de tous les auteurs
     */
    @GetMapping
    public List<Auteur> getAll() {
        return service.getAll();
    }

    /**
     * GET http://localhost:8080/api/auteurs/{id}
     * Récupère un auteur en fonction de son ID
     */
    @GetMapping("/{id}")
    public Auteur getById(@PathVariable Long id) {
        return service.getById(id);
    }

    /**
     * POST http://localhost:8080/api/auteurs
     * Crée un nouvel auteur (à envoyer dans le body de la requête)
     */
    @PostMapping
    public Auteur create(@RequestBody Auteur auteur) {
        return service.create(auteur);
    }

    /**
     * PUT http://localhost:8080/api/auteurs/{id}
     * Met à jour un auteur existant en fonction de son ID
     */
    @PutMapping("/{id}")
    public Auteur update(@PathVariable Long id, @RequestBody Auteur auteur) {
        return service.update(id, auteur);
    }

    /**
     * DELETE http://localhost:8080/api/auteurs/{id}
     * Supprime un auteur par son ID
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
