package com.example.auteur_service.repository;

// Importation de l'entité
import com.example.auteur_service.entity.Auteur;
// Importation de l'interface JpaRepository de Spring Data
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface qui hérite de JpaRepository pour manipuler l'entité Auteur.
 * Elle donne accès automatiquement à toutes les opérations CRUD :
 * - findAll(), findById(), save(), deleteById(), etc.
 */
public interface AuteurRepository extends JpaRepository<Auteur, Long> {
    // Pas besoin d'ajouter de méthodes ici pour les opérations de base
}
