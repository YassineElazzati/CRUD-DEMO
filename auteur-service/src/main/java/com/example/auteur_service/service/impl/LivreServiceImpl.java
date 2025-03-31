package com.example.auteur_service.service.impl;

import com.example.auteur_service.entity.Livre;
import com.example.auteur_service.repository.LivreRepository;
import com.example.auteur_service.service.LivreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivreServiceImpl implements LivreService {

    private final LivreRepository livreRepository;

    @Override
    public Livre saveLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    @Override
    public Optional<Livre> getLivreById(Long id) {
        return livreRepository.findById(id);
    }

    @Override
    public Livre updateLivre(Long id, Livre livre) {
        Livre existing = livreRepository.findById(id).orElseThrow();
        existing.setTitre(livre.getTitre());
        existing.setAuteur(livre.getAuteur());
        return livreRepository.save(existing);
    }

    @Override
    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }
}
