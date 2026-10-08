package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Maintenance;
import tn.esprit.autoloc.Repositories.IMaintenanceRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class MaintenanceServiceImpl implements IMaintenance {
    private final IMaintenanceRepo maintenanceRepo;


    @Override
    public Maintenance ajouterMaintenance(Maintenance ma) {
        return maintenanceRepo.save(ma);
    }

    @Override
    public void  supprimerMaintenance(long idMaintenance) {
        maintenanceRepo.deleteById(idMaintenance);
    }

    @Override
    public List<Maintenance> recupererMaintenances() {
        return maintenanceRepo.findAll();
    }

    @Override
    public Set<Maintenance> findMaintenances() {
        return new HashSet<>(maintenanceRepo.findAll());
    }

    @Override
    public Maintenance recupererMaintenanceById(long idMaintenance) {
        return maintenanceRepo.findById(idMaintenance).get();
    }
}
