package tn.esprit.autoloc.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Services.IAgence;

import java.util.List;

@RestController
@RequestMapping("/api/Agence")
@RequiredArgsConstructor

public class AgenceController {
    private final IAgence iAgence;

    @PostMapping("/AddAgence")
    public Agence ajouterAgence(@RequestBody Agence agence){
        return iAgence.ajouterAgence(agence);
    }
    @GetMapping("/GetAll")
    public List<Agence> getAll(){
        return iAgence.recupererAgences();
    }
    @GetMapping("/GetAgence/{id}")
    public Agence getAgence(@PathVariable Long id){
        return iAgence.recupererAgenceById(id);
    }
    @DeleteMapping("/DeleteAgence/{id}")
    public void deleteAgence(@PathVariable Long id){
         iAgence.supprimerAgence(id);
    }
    @PutMapping("/EditAgence")
    public Agence updateAgence(@RequestBody Agence agence){
        return iAgence.modifierAgence(agence);
    }

}
