package tn.esprit.autoloc.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Equipement;
import tn.esprit.autoloc.Services.IEquipement;

import java.util.List;

@RestController
@RequestMapping("/api/Equipement")
@RequiredArgsConstructor
@Tag(name="Gestion Equipement")
public class EquipementController {
    private final IEquipement iEquipement;

    @Operation(description="ajouter un objet équipement dans la BD")
    @PostMapping("/AddEquipement")
    public Equipement ajouterEquipement(@Valid @RequestBody Equipement equipement){
        return iEquipement.ajouterEquipement(equipement);
    }
    @Operation(description="Récuperer la liste des équipements")
    @GetMapping("/GetAll")
    public List<Equipement> getAll(){
        return iEquipement.recupererEquipements();
    }
    @Operation(description="Récuperer un équipement par son id")
    @GetMapping("/GetEquipement/{id}")
    public Equipement getEquipement(@PathVariable Long id){
        return iEquipement.recupererEquipementById(id);
    }
    @Operation(description="supprimer un équipement par son id")
    @DeleteMapping("/DeleteEquipement/{id}")
    public void deleteEquipement(@PathVariable Long id){
         iEquipement.supprimerEquipement(id);
    }
    @Operation(description="modifier un objet équipement dans la BD")
    @PutMapping("/EditEquipement/{id}")
    public Equipement updateEquipement(@PathVariable Long id, @RequestBody Equipement equipement){
        equipement.setIdEquipement(id);
        return iEquipement.modifierEquipement(equipement);
    }

}
