package tn.esprit.autoloc.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Paiement;
import tn.esprit.autoloc.Services.IPaiement;

import java.util.List;

@RestController
@RequestMapping("/api/Paiement")
@RequiredArgsConstructor

public class PaiementController {
    private final IPaiement iPaiement;

    @GetMapping("/GetAll")
    public List<Paiement> getAll(){
        return iPaiement.recupererPaiements();
    }
    @GetMapping("/GetPaiement/{id}")
    public Paiement getPaiement(@PathVariable Long id){
        return iPaiement.recupererPaiementById(id);
    }

}
