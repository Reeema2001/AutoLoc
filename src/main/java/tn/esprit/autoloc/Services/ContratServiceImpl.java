package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Contrat;
import tn.esprit.autoloc.Repositories.IContratRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class ContratServiceImpl implements IContrat {
    private final IContratRepo contratRepo;


    @Override
    public Contrat ajouterContrat(Contrat co) {
        return contratRepo.save(co);
    }

    @Override
    public void  supprimerContrat(long idContrat) {
        contratRepo.deleteById(idContrat);
    }

    @Override
    public List<Contrat> recupererContrats() {
        return contratRepo.findAll();
    }

    @Override
    public Set<Contrat> findContrats() {
        return new HashSet<>(contratRepo.findAll());
    }

    @Override
    public Contrat recupererContratById(long idContrat) {
        return contratRepo.findById(idContrat).get();
    }
}
