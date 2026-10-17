package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Maintenance;

import java.util.List;
import java.util.Set;

public interface IMaintenance {
    Maintenance ajouterMaintenance(Maintenance ma);
    void supprimerMaintenance(Long idMaintenance);
    List<Maintenance> recupererMaintenances();
    Set<Maintenance> findMaintenances();
    Maintenance recupererMaintenanceById (Long idMaintenance);

}
