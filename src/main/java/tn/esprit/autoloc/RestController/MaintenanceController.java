package tn.esprit.autoloc.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Maintenance;
import tn.esprit.autoloc.Services.IMaintenance;

import java.util.List;

@RestController
@RequestMapping("/api/Maintenance")
@RequiredArgsConstructor

public class MaintenanceController {
    private final IMaintenance iMaintenance;

    @PostMapping("/AddMaintenance")
    public Maintenance ajouterMaintenance(@RequestBody Maintenance maintenance){
        return iMaintenance.ajouterMaintenance(maintenance);
    }
    @GetMapping("/GetAll")
    public List<Maintenance> getAll(){
        return iMaintenance.recupererMaintenances();
    }
    @GetMapping("/GetMaintenance/{id}")
    public Maintenance getMaintenance(@PathVariable Long id){
        return iMaintenance.recupererMaintenanceById(id);
    }
    @DeleteMapping("/DeleteMaintenance/{id}")
    public void deleteMaintenance(@PathVariable Long id){
         iMaintenance.supprimerMaintenance(id);
    }
    @PutMapping("/EditMaintenance")
    public Maintenance updateMaintenance(@RequestBody Maintenance maintenance){
        return iMaintenance.modifierMaintenance(maintenance);
    }

}
