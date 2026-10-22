package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Exceptions.ResourceNotFoundException;
import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Repositories.IAgenceRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class AgenceServiceImpl implements IAgence {
    private final IAgenceRepo agenceRepo;


    @Override
    public Agence ajouterAgence(Agence ag) {
        return agenceRepo.save(ag);
    }

    @Override
    public Agence modifierAgence(Agence ag) {
        return agenceRepo.save(ag);
    }

    @Override
    public void  supprimerAgence(Long idAgence) {
        agenceRepo.deleteById(idAgence);
    }

    @Override
    public List<Agence> recupererAgences() {
        return agenceRepo.findAll();
    }

    @Override
    public Set<Agence> findAgences() {
        return new HashSet<>(agenceRepo.findAll());
    }

    @Override
    public Agence recupererAgenceById(Long idAgence) {
        // Avant (java:S3655 : Optional.get() appelé sans vérifier que la valeur existe)
        // return agenceRepo.findById(idAgence).get();
        return agenceRepo.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", idAgence));
    }
}
