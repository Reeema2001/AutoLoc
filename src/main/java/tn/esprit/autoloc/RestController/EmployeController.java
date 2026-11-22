package tn.esprit.autoloc.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Services.IEmploye;

import java.util.List;

@RestController
@RequestMapping("/api/Employe")
@RequiredArgsConstructor
@Tag(name="Gestion Employe")
public class EmployeController {
    private final IEmploye iEmploye;

    @Operation(description="ajouter un objet employé dans la BD")
    @PostMapping("/AddEmploye")
    public Employe ajouterEmploye(@Valid @RequestBody Employe employe){
        return iEmploye.ajouterEmploye(employe);
    }
    @Operation(description="Récuperer la liste des employés")
    @GetMapping("/GetAll")
    public List<Employe> getAll(){
        return iEmploye.recupererEmployes();
    }
    @Operation(description="Récuperer un employé par son id")
    @GetMapping("/GetEmploye/{id}")
    public Employe getEmploye(@PathVariable Long id){
        return iEmploye.recupererEmployeById(id);
    }
    @Operation(description="supprimer un employé par son id")
    @DeleteMapping("/DeleteEmploye/{id}")
    public void deleteEmploye(@PathVariable Long id){
         iEmploye.supprimerEmploye(id);
    }
    @Operation(description="modifier un objet employé dans la BD")
    @PutMapping("/EditEmploye/{id}")
    public Employe updateEmploye(@PathVariable Long id, @RequestBody Employe employe){
        employe.setIdEmploye(id);
        return iEmploye.modifierEmploye(employe);
    }

    @Operation(description="désaffecter un employé de son agence")
    @PutMapping("/desaffecterEmploye/{idEmploye}")
    public Employe desaffecterEmploye(@PathVariable Long idEmploye){
        return iEmploye.desaffecterEmploye(idEmploye);
    }
    @Operation(description="affecter un employé à une agence")
    @PutMapping("/affecterEmploye/{idEmploye}/{idAgence}")
    public Employe affecterEmploye(@PathVariable Long idEmploye, @PathVariable Long idAgence){
        return iEmploye.affecterEmploye(idEmploye, idAgence);
    }

}
