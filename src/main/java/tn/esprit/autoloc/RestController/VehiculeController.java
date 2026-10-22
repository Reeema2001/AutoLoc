package tn.esprit.autoloc.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Vehicule;
import tn.esprit.autoloc.Services.IVehicule;

import java.util.List;

@RestController
@RequestMapping("/api/Vehicule")
@RequiredArgsConstructor

public class VehiculeController {
    private final IVehicule iVehicule;

    @PostMapping("/AddVehicule")
    public Vehicule ajouterVehicule(@RequestBody Vehicule vehicule){
        return iVehicule.ajouterVehicule(vehicule);
    }
    @GetMapping("/GetAll")
    public List<Vehicule> getAll(){
        return iVehicule.recupererVehicules();
    }
    @GetMapping("/GetVehicule/{id}")
    public Vehicule getVehicule(@PathVariable Long id){
        return iVehicule.recupererVehiculeById(id);
    }
    @DeleteMapping("/DeleteVehicule/{id}")
    public void deleteVehicule(@PathVariable Long id){
         iVehicule.supprimerVehicule(id);
    }
    @PutMapping("/EditVehicule")
    public Vehicule updateVehicule(@RequestBody Vehicule vehicule){
        return iVehicule.modifierVehicule(vehicule);
    }

}
