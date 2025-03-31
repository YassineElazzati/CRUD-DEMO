package com.example.auteur_service.service.impl;

import com.example.auteur_service.entity.Livre;
import com.example.auteur_service.repository.LivreRepository;
import com.example.auteur_service.service.LivreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Annotation qui indique que cette classe est un service Spring
@Service
@RequiredArgsConstructor // Lombok crée le constructeur avec le repository injecté
public class LivreServiceImpl implements LivreService {

    private final LivreRepository livreRepository;

    /**
     * Sauvegarde un livre (création)
     */
    @Override
    public Livre saveLivre(Livre livre) {
        return livreRepository.save(livre); // Méthode JPA pour insert/update
    }

    /**
     * Récupère tous les livres
     */
    @Override
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    /**
     * Récupère un livre par ID
     */
    @Override
    public Optional<Livre> getLivreById(Long id) {
        return livreRepository.findById(id);
    }

    /**
     * Met à jour un livre existant
     */
    @Override
    public Livre updateLivre(Long id, Livre livre) {
        // On récupère le livre existant, ou on lance une exception s'il n'existe pas
        Livre existing = livreRepository.findById(id).orElseThrow();
        // On met à jour les champs nécessaires
        existing.setTitre(livre.getTitre());
        existing.setAuteur(livre.getAuteur());
        return livreRepository.save(existing);
    }

    /**
     * Supprime un livre par ID
     */
    @Override
    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }
}
