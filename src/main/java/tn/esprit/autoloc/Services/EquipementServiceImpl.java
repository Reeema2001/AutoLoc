package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Entities.Equipement;
import tn.esprit.autoloc.Repositories.IEquipementRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class EquipementServiceImpl implements IEquipement {
    private final IEquipementRepo equipementRepo;


    @Override
    public Equipement ajouterEquipement(Equipement eq) {
        return equipementRepo.save(eq);
    }

    @Override
    public void  supprimerEquipement(long idEquipement) {
        equipementRepo.deleteById(idEquipement);
    }

    @Override
    public List<Equipement> recupererEquipements() {
        return equipementRepo.findAll();
    }

    @Override
    public Set<Equipement> findEquipements() {
        return new HashSet<>(equipementRepo.findAll());
    }

    @Override
    public Equipement recupererEquipementById(long idEquipement) {
        return equipementRepo.findById(idEquipement).get();
    }
}
