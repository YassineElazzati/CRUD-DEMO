package com.example.auteur_service.service.impl;

import com.example.auteur_service.entity.Auteur;
import com.example.auteur_service.repository.AuteurRepository;
import com.example.auteur_service.service.AuteurService;
import org.springframework.stereotype.Service;

import java.util.List;

// Cette classe contient toute la logique métier liée aux auteurs
@Service
public class AuteurServiceImpl implements AuteurService {

    // Référence au repository pour interagir avec la base de données
    private final AuteurRepository repository;

    // Constructeur avec injection de dépendance du repository
    public AuteurServiceImpl(AuteurRepository repository) {
        this.repository = repository;
    }

    /**
     * Récupère la liste complète des auteurs dans la base de données
     */
    public List<Auteur> getAll() {
        return repository.findAll(); // Méthode JPA qui retourne tous les enregistrements
    }

    /**
     * Récupère un auteur par son ID
     */
    public Auteur getById(Long id) {
        // On utilise findById, qui retourne un Optional. Si absent, on retourne null.
        return repository.findById(id).orElse(null);
    }

    /**
     * Crée un nouvel auteur dans la base de données
     */
    public Auteur create(Auteur auteur) {
        return repository.save(auteur); // Enregistre l'auteur (insert en BDD)
    }

    /**
     * Met à jour un auteur existant
     */
    public Auteur update(Long id, Auteur auteur) {
        Auteur existing = getById(id); // On vérifie si l'auteur existe
        if (existing == null) return null; // Si non trouvé, on retourne null
        existing.setNom(auteur.getNom()); // On met à jour le nom 
        return repository.save(existing); // On sauvegarde les modifications
    }

    /**
     * Supprime un auteur par son ID
     */
    public void delete(Long id) {
        repository.deleteById(id); // Supprime l'enregistrement avec l'ID donné
    }
}
