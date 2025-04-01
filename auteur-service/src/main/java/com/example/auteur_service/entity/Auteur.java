package com.example.auteur_service.entity;

import jakarta.persistence.*;   // Pour les annotations JPA (Entity, Id, etc.)
import lombok.*;               // Pour générer automatiquement les getters/setters, etc.

/**
 * Représente un auteur dans la base de données.
 */
@Entity // Dit à JPA que cette classe représente une table en base
@Table(name = "auteur") // Associe cette entité à la table "auteur"
@Data // Lombok : génère getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok : génère un constructeur sans argument
@AllArgsConstructor // Lombok : génère un constructeur avec tous les champs
@Builder // Lombok : permet d’utiliser le pattern builder pour créer des objets
public class Auteur {

    @Id // Définit la clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémenté (comme un SERIAL en SQL)
    @Column(name = "id_auteur") // Associe ce champ à la colonne "id_auteur"
    private Long id;

    private String nom; // Le nom de l’auteur (sera mappé automatiquement à une colonne "nom")
}
