package com.example.auteur_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "livre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livre")
    private Long id;

    private String titre;

    @ManyToOne
    @JoinColumn(name = "id_auteur", nullable = false)
    private Auteur auteur;
}
