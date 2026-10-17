package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Employe;

import java.util.List;
import java.util.Set;

public interface IEmploye {
    Employe ajouterEmploye(Employe em);
    void supprimerEmploye(Long idEmploye);
    List<Employe> recupererEmployes();
    Set<Employe> findEmployes();
    Employe recupererEmployeById (Long idEmploye);

}
