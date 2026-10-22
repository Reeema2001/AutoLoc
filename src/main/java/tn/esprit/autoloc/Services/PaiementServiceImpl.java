package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Exceptions.ResourceNotFoundException;
import tn.esprit.autoloc.Entities.Paiement;
import tn.esprit.autoloc.Repositories.IPaiementRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class PaiementServiceImpl implements IPaiement {
    private final IPaiementRepo paiementRepo;


    @Override
    public Paiement ajouterPaiement(Paiement pa) {
        return paiementRepo.save(pa);
    }

    @Override
    public void  supprimerPaiement(Long idPaiement) {
        paiementRepo.deleteById(idPaiement);
    }

    @Override
    public List<Paiement> recupererPaiements() {
        return paiementRepo.findAll();
    }

    @Override
    public Set<Paiement> findPaiements() {
        return new HashSet<>(paiementRepo.findAll());
    }

    @Override
    public Paiement recupererPaiementById(Long idPaiement) {
        // Avant (java:S3655 : Optional.get() appelé sans vérifier que la valeur existe)
        // return paiementRepo.findById(idPaiement).get();
        return paiementRepo.findById(idPaiement)
                .orElseThrow(() -> new ResourceNotFoundException("Paiement", idPaiement));
    }
}
