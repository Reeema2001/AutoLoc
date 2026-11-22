package tn.esprit.autoloc.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Contrat;
import tn.esprit.autoloc.Services.IContrat;

import java.util.List;

@RestController
@RequestMapping("/api/Contrat")
@RequiredArgsConstructor
@Tag(name="Gestion Contrat")
public class ContratController {
    private final IContrat iContrat;

    @Operation(description="ajouter un objet contrat dans la BD")
    @PostMapping("/AddContrat")
    public Contrat ajouterContrat(@Valid @RequestBody Contrat contrat){
        return iContrat.ajouterContrat(contrat);
    }
    @Operation(description="Récuperer la liste des contrats")
    @GetMapping("/GetAll")
    public List<Contrat> getAll(){
        return iContrat.recupererContrats();
    }
    @Operation(description="Récuperer un contrat par son id")
    @GetMapping("/GetContrat/{id}")
    public Contrat getContrat(@PathVariable Long id){
        return iContrat.recupererContratById(id);
    }
    @Operation(description="supprimer un contrat par son id")
    @DeleteMapping("/DeleteContrat/{id}")
    public void deleteContrat(@PathVariable Long id){
         iContrat.supprimerContrat(id);
    }
    @Operation(description="modifier un objet contrat dans la BD")
    @PutMapping("/EditContrat/{id}")
    public Contrat updateContrat(@PathVariable Long id, @RequestBody Contrat contrat){
        contrat.setIdContrat(id);
        return iContrat.modifierContrat(contrat);
    }

}
