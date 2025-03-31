package com.example.auteur_service.service.impl;

import com.example.auteur_service.entity.Auteur;
import com.example.auteur_service.repository.AuteurRepository;
import com.example.auteur_service.service.AuteurService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuteurServiceImpl implements AuteurService {

    private final AuteurRepository repository;

    public AuteurServiceImpl(AuteurRepository repository) {
        this.repository = repository;
    }

    public List<Auteur> getAll() {
        return repository.findAll();
    }

    public Auteur getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Auteur create(Auteur auteur) {
        return repository.save(auteur);
    }

    public Auteur update(Long id, Auteur auteur) {
        Auteur existing = getById(id);
        if (existing == null) return null;
        existing.setNom(auteur.getNom());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
