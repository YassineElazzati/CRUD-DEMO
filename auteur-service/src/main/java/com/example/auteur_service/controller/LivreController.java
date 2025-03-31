package com.example.auteur_service.controller;

import com.example.auteur_service.entity.Livre;
import com.example.auteur_service.service.LivreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
@RequiredArgsConstructor
public class LivreController {

    private final LivreService livreService;

    @PostMapping
    public ResponseEntity<Livre> create(@RequestBody Livre livre) {
        return ResponseEntity.ok(livreService.saveLivre(livre));
    }

    @GetMapping
    public ResponseEntity<List<Livre>> getAll() {
        return ResponseEntity.ok(livreService.getAllLivres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> getById(@PathVariable Long id) {
        return livreService.getLivreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livre> update(@PathVariable Long id, @RequestBody Livre livre) {
        return ResponseEntity.ok(livreService.updateLivre(id, livre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livreService.deleteLivre(id);
        return ResponseEntity.noContent().build();
    }
}
