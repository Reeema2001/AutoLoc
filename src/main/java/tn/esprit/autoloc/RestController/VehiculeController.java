package tn.esprit.autoloc.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Vehicule;
import tn.esprit.autoloc.Services.IVehicule;

import java.util.List;

@RestController
@RequestMapping("/api/Vehicule")
@RequiredArgsConstructor
@Tag(name="Gestion Vehicule")
public class VehiculeController {
    private final IVehicule iVehicule;

    @Operation(description="ajouter un objet véhicule dans la BD")

    @PostMapping("/AddVehicule")
    public Vehicule ajouterVehicule(@Valid @RequestBody Vehicule vehicule){
        return iVehicule.ajouterVehicule(vehicule);
    }


    @Operation(description="Récuperer la liste des véhicules")
    @GetMapping("/GetAll")
    public List<Vehicule> getAll(){
        return iVehicule.recupererVehicules();
    }
    @Operation(description="Récuperer un véhicule par son id")
    @GetMapping("/GetVehicule/{id}")
    public Vehicule getVehicule(@PathVariable Long id){
        return iVehicule.recupererVehiculeById(id);
    }
    @Operation(description="supprimer un véhicule par son id")
    @DeleteMapping("/DeleteVehicule/{id}")
    public void deleteVehicule(@PathVariable Long id){
         iVehicule.supprimerVehicule(id);
    }
    @Operation(description="modifier un objet véhicule dans la BD")
    @PutMapping("/EditVehicule/{id}")
    public Vehicule updateVehicule(@PathVariable Long id, @RequestBody Vehicule vehicule){
        vehicule.setIdVehicule(id);
        return iVehicule.modifierVehicule(vehicule);
    }

    @Operation(description="affecter un véhicule à une agence")
    @PutMapping("/affecterVehicule/{idVehicule}/{idAgence}")
    public Vehicule affecterVehicule(@PathVariable Long idVehicule, @PathVariable Long idAgence){
        return iVehicule.affecterVehicule(idVehicule, idAgence);
    }
    @Operation(description="affecter un équipement à un véhicule")
    @PutMapping("/affecterEquipement/{idVehicule}/{idEquipement}")
    public Vehicule affecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement){
        return iVehicule.affecterEquipement(idVehicule, idEquipement);
    }
    @Operation(description="désaffecter un équipement d'un véhicule")
    @PutMapping("/desaffecterEquipement/{idVehicule}/{idEquipement}")
    public Vehicule desaffecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement){
        return iVehicule.desaffecterEquipement(idVehicule, idEquipement);
    }

}
