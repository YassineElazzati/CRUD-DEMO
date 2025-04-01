package com.example.auteur_service.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Représente un livre dans la base de données.
 */
@Entity // Cette classe est une entité JPA (une table)
@Table(name = "livre") // Liée à la table "livre"
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livre")
    private Long id;

    private String titre; // Titre du livre

    @ManyToOne // Relation plusieurs livres → un seul auteur
    @JoinColumn(name = "id_auteur", nullable = false) // Clé étrangère vers la table "auteur"
    private Auteur auteur; // L’auteur associé à ce livre
}
