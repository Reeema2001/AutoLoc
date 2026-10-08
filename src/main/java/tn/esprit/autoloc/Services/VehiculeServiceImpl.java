package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Vehicule;
import tn.esprit.autoloc.Repositories.IVehiculeRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class VehiculeServiceImpl implements IVehicule {
    private final IVehiculeRepo vehiculeRepo;


    @Override
    public Vehicule ajouterVehicule(Vehicule ve) {
        return vehiculeRepo.save(ve);
    }

    @Override
    public void  supprimerVehicule(long idVehicule) {
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
    public Vehicule recupererVehiculeById(long idVehicule) {
        return vehiculeRepo.findById(idVehicule).get();
    }
}
