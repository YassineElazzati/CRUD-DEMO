package com.example.auteur_service.service;

import com.example.auteur_service.entity.Livre;

import java.util.List;
import java.util.Optional;

public interface LivreService {
    Livre saveLivre(Livre livre);
    List<Livre> getAllLivres();
    Optional<Livre> getLivreById(Long id);
    Livre updateLivre(Long id, Livre livre);
    void deleteLivre(Long id);
}
