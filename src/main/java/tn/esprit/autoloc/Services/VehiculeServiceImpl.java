package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Entities.Equipement;
import tn.esprit.autoloc.Exceptions.ResourceNotFoundException;
import tn.esprit.autoloc.Entities.Vehicule;
import tn.esprit.autoloc.Repositories.IAgenceRepo;
import tn.esprit.autoloc.Repositories.IEquipementRepo;
import tn.esprit.autoloc.Repositories.IVehiculeRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class VehiculeServiceImpl implements IVehicule {
    private final IVehiculeRepo vehiculeRepo;
    private final IAgenceRepo agenceRepo;
    private final IEquipementRepo equipementRepo;


    @Override
    public Vehicule ajouterVehicule(Vehicule ve) {
        return vehiculeRepo.save(ve);
    }

    @Override
    public Vehicule modifierVehicule(Vehicule ve) {
        return vehiculeRepo.save(ve);
    }

    @Override
    public void  supprimerVehicule(Long idVehicule) {
        vehiculeRepo.deleteById(idVehicule);
    }

    @Override
    public List<Vehicule> recupererVehicules() {
        return vehiculeRepo.findAll();
    }

    @Override
    public Set<Vehicule> findVehicules() {
        return new HashSet<>(vehiculeRepo.findAll());
    }

    @Override
    public Vehicule recupererVehiculeById(Long idVehicule) {
        /*
         Avant (java:S3655 : Optional.get() appelé sans vérifier que la valeur existe)
         return vehiculeRepo.findById(idVehicule).get();
         */
        return vehiculeRepo.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", idVehicule));
    }

    // Atelier 6 : ManyToOne Vehicule -> Agence (côté propriétaire : Vehicule.agence)
    @Override
    @Transactional
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule vehicule = vehiculeRepo.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", idVehicule));
        Agence agence = agenceRepo.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", idAgence));
        vehicule.setAgence(agence);
        return vehiculeRepo.save(vehicule);
    }

    // Atelier 6 : ManyToMany Vehicule <-> Equipement (côté propriétaire : Vehicule.equipements)
    @Override
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepo.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", idVehicule));
        Equipement equipement = equipementRepo.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", idEquipement));
        vehicule.getEquipements().add(equipement);
        return vehiculeRepo.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepo.findById(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", idVehicule));
        Equipement equipement = equipementRepo.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", idEquipement));
        vehicule.getEquipements().remove(equipement);
        return vehiculeRepo.save(vehicule);
    }
}
