package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Agence;

import java.util.List;
import java.util.Set;

public interface IAgence {
    Agence ajouterAgence(Agence ag);
    Agence modifierAgence(Agence ag);
    void supprimerAgence(Long idAgence);
    List<Agence> recupererAgences();
    Set<Agence> findAgences();
    Agence recupererAgenceById (Long idAgence);

}
