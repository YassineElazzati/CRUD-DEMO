package com.example.auteur_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "auteur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auteur")
    private Long id;

    private String nom;
}
