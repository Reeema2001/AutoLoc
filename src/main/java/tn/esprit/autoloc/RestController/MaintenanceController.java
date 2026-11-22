package tn.esprit.autoloc.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Maintenance;
import tn.esprit.autoloc.Services.IMaintenance;

import java.util.List;

@RestController
@RequestMapping("/api/Maintenance")
@RequiredArgsConstructor
@Tag(name="Gestion Maintenance")
public class MaintenanceController {
    private final IMaintenance iMaintenance;

    @Operation(description="ajouter un objet maintenance dans la BD")
    @PostMapping("/AddMaintenance")
    public Maintenance ajouterMaintenance(@Valid @RequestBody Maintenance maintenance){
        return iMaintenance.ajouterMaintenance(maintenance);
    }
    @Operation(description="Récuperer la liste des maintenances")
    @GetMapping("/GetAll")
    public List<Maintenance> getAll(){
        return iMaintenance.recupererMaintenances();
    }
    @Operation(description="Récuperer une maintenance par son id")
    @GetMapping("/GetMaintenance/{id}")
    public Maintenance getMaintenance(@PathVariable Long id){
        return iMaintenance.recupererMaintenanceById(id);
    }
    @Operation(description="supprimer une maintenance par son id")
    @DeleteMapping("/DeleteMaintenance/{id}")
    public void deleteMaintenance(@PathVariable Long id){
         iMaintenance.supprimerMaintenance(id);
    }
    @Operation(description="modifier un objet maintenance dans la BD")
    @PutMapping("/EditMaintenance/{id}")
    public Maintenance updateMaintenance(@PathVariable Long id, @RequestBody Maintenance maintenance){
        maintenance.setIdMaintenance(id);
        return iMaintenance.modifierMaintenance(maintenance);
    }

}
