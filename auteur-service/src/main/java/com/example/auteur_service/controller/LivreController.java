package com.example.auteur_service.controller;

import com.example.auteur_service.entity.Livre;
import com.example.auteur_service.service.LivreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Cette classe est un contrôleur REST pour l'entité Livre
@RestController
@RequestMapping("/api/livres") // Toutes les routes commencent par http://localhost:8080/api/livres
@RequiredArgsConstructor // Lombok génère automatiquement le constructeur avec les arguments requis
public class LivreController {

    // Injection du service métier lié aux livres
    private final LivreService livreService;

    /**
     * POST http://localhost:8080/api/livres
     * Crée un nouveau livre
     */
    @PostMapping
    public ResponseEntity<Livre> create(@RequestBody Livre livre) {
        // Retourne le livre créé avec un code 200 (OK)
        return ResponseEntity.ok(livreService.saveLivre(livre));
    }

    /**
     * GET http://localhost:8080/api/livres
     * Récupère la liste de tous les livres
     */
    @GetMapping
    public ResponseEntity<List<Livre>> getAll() {
        return ResponseEntity.ok(livreService.getAllLivres());
    }

    /**
     * GET http://localhost:8080/api/livres/{id}
     * Récupère un livre par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Livre> getById(@PathVariable Long id) {
        return livreService.getLivreById(id)
                .map(ResponseEntity::ok) // Si trouvé, retourne le livre
                .orElse(ResponseEntity.notFound().build()); // Sinon, retourne 404
    }

    /**
     * PUT http://localhost:8080/api/livres/{id}
     * Met à jour un livre existant
     */
    @PutMapping("/{id}")
    public ResponseEntity<Livre> update(@PathVariable Long id, @RequestBody Livre livre) {
        return ResponseEntity.ok(livreService.updateLivre(id, livre));
    }

    /**
     * DELETE http://localhost:8080/api/livres/{id}
     * Supprime un livre par son ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livreService.deleteLivre(id);
        return ResponseEntity.noContent().build(); // Retourne un code 204 sans contenu
    }
}
