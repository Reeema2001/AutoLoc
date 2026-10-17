package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        return agenceRepo.findById(idAgence).get();
    }
}
