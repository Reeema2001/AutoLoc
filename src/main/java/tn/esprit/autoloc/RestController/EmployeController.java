package tn.esprit.autoloc.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Services.IEmploye;

import java.util.List;

@RestController
@RequestMapping("/api/Employe")
@RequiredArgsConstructor

public class EmployeController {
    private final IEmploye iEmploye;

    @PostMapping("/AddEmploye")
    public Employe ajouterEmploye(@RequestBody Employe employe){
        return iEmploye.ajouterEmploye(employe);
    }
    @GetMapping("/GetAll")
    public List<Employe> getAll(){
        return iEmploye.recupererEmployes();
    }
    @GetMapping("/GetEmploye/{id}")
    public Employe getEmploye(@PathVariable Long id){
        return iEmploye.recupererEmployeById(id);
    }
    @DeleteMapping("/DeleteEmploye/{id}")
    public void deleteEmploye(@PathVariable Long id){
         iEmploye.supprimerEmploye(id);
    }
    @PutMapping("/EditEmploye")
    public Employe updateEmploye(@RequestBody Employe employe){
        return iEmploye.modifierEmploye(employe);
    }

}
