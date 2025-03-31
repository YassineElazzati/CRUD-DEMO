package com.example.auteur_service.repository;

import com.example.auteur_service.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface qui permet d’interagir avec la base de données pour l'entité Livre.
 * Hérite de JpaRepository<Livre, Long> pour disposer de toutes les méthodes CRUD :
 * - findAll(), findById(), save(), deleteById(), etc.
 */
public interface LivreRepository extends JpaRepository<Livre, Long> {
}
