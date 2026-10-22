package tn.esprit.autoloc.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Equipement;
import tn.esprit.autoloc.Services.IEquipement;

import java.util.List;

@RestController
@RequestMapping("/api/Equipement")
@RequiredArgsConstructor

public class EquipementController {
    private final IEquipement iEquipement;

    @PostMapping("/AddEquipement")
    public Equipement ajouterEquipement(@RequestBody Equipement equipement){
        return iEquipement.ajouterEquipement(equipement);
    }
    @GetMapping("/GetAll")
    public List<Equipement> getAll(){
        return iEquipement.recupererEquipements();
    }
    @GetMapping("/GetEquipement/{id}")
    public Equipement getEquipement(@PathVariable Long id){
        return iEquipement.recupererEquipementById(id);
    }
    @DeleteMapping("/DeleteEquipement/{id}")
    public void deleteEquipement(@PathVariable Long id){
         iEquipement.supprimerEquipement(id);
    }
    @PutMapping("/EditEquipement")
    public Equipement updateEquipement(@RequestBody Equipement equipement){
        return iEquipement.modifierEquipement(equipement);
    }

}
