package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Contrat;

import java.util.List;
import java.util.Set;

public interface IContrat {
    Contrat ajouterContrat(Contrat co);
    Contrat modifierContrat(Contrat co);
    void supprimerContrat(Long idContrat);
    List<Contrat> recupererContrats();
    Set<Contrat> findContrats();
    Contrat recupererContratById (Long idContrat);

}
