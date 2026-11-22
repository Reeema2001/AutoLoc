package tn.esprit.autoloc.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Paiement;
import tn.esprit.autoloc.Services.IPaiement;

import java.util.List;

@RestController
@RequestMapping("/api/Paiement")
@RequiredArgsConstructor
@Tag(name="Gestion Paiement")
public class PaiementController {
    private final IPaiement iPaiement;

    @Operation(description="Récuperer la liste des paiements")
    @GetMapping("/GetAll")
    public List<Paiement> getAll(){
        return iPaiement.recupererPaiements();
    }
    @Operation(description="Récuperer un paiement par son id")
    @GetMapping("/GetPaiement/{id}")
    public Paiement getPaiement(@PathVariable Long id){
        return iPaiement.recupererPaiementById(id);
    }

}
