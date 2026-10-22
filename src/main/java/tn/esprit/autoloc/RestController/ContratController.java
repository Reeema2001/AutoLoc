package tn.esprit.autoloc.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Contrat;
import tn.esprit.autoloc.Services.IContrat;

import java.util.List;

@RestController
@RequestMapping("/api/Contrat")
@RequiredArgsConstructor

public class ContratController {
    private final IContrat iContrat;

    @PostMapping("/AddContrat")
    public Contrat ajouterContrat(@RequestBody Contrat contrat){
        return iContrat.ajouterContrat(contrat);
    }
    @GetMapping("/GetAll")
    public List<Contrat> getAll(){
        return iContrat.recupererContrats();
    }
    @GetMapping("/GetContrat/{id}")
    public Contrat getContrat(@PathVariable Long id){
        return iContrat.recupererContratById(id);
    }
    @DeleteMapping("/DeleteContrat/{id}")
    public void deleteContrat(@PathVariable Long id){
         iContrat.supprimerContrat(id);
    }
    @PutMapping("/EditContrat")
    public Contrat updateContrat(@RequestBody Contrat contrat){
        return iContrat.modifierContrat(contrat);
    }

}
