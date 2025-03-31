package com.example.auteur_service.service;

import com.example.auteur_service.entity.Auteur;
import java.util.List;

public interface AuteurService {
    List<Auteur> getAll();
    Auteur getById(Long id);
    Auteur create(Auteur auteur);
    Auteur update(Long id, Auteur auteur);
    void delete(Long id);
}
