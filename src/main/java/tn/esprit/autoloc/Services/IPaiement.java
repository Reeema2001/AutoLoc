package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Paiement;

import java.util.List;
import java.util.Set;

public interface IPaiement {
    Paiement ajouterPaiement(Paiement pa);
    void supprimerPaiement(long idPaiement);
    List<Paiement> recupererPaiements();
    Set<Paiement> findPaiements();
    Paiement recupererPaiementById (long idPaiement);

}
