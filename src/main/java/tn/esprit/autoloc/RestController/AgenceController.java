package tn.esprit.autoloc.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Agence;
import tn.esprit.autoloc.Services.IAgence;

import java.util.List;

@RestController
@RequestMapping("/api/Agence")
@RequiredArgsConstructor
@Tag(name="Gestion Agence")
public class AgenceController {
    private final IAgence iAgence;
    @Operation(description="ajouter un objet agence dans la BD")

    @PostMapping("/AddAgence")
    public Agence ajouterAgence(@Valid @RequestBody Agence agence){
        return iAgence.ajouterAgence(agence);
    }

    @Operation(description="Récuperer la liste des agences ")

    @GetMapping("/GetAll")
    public List<Agence> getAll(){
        return iAgence.recupererAgences();
    }
    @Operation(description="Récuperer une agence par son id")
    @GetMapping("/GetAgence/{id}")
    public Agence getAgence(@PathVariable Long id){
        return iAgence.recupererAgenceById(id);
    }
    @Operation(description="supprimer une agence par son id")
    @DeleteMapping("/DeleteAgence/{id}")
    public void deleteAgence(@PathVariable Long id){
         iAgence.supprimerAgence(id);
    }
    @Operation(description="modifier un objet agence dans la BD")
    @PutMapping("/EditAgence/{id}")
    public Agence updateAgence(@PathVariable Long id, @RequestBody Agence agence){
        agence.setIdAgence(id);
        return iAgence.modifierAgence(agence);
    }

}
