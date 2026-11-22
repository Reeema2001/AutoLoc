package tn.esprit.autoloc.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Client;
import tn.esprit.autoloc.Services.IClient;

import java.util.List;

@RestController
@RequestMapping("/api/Client")
@RequiredArgsConstructor
@Tag(name="Gestion Client")
public class ClientController {
    private final IClient iClient;

    @Operation(description="ajouter un objet client dans la BD")
    @PostMapping("/AddClient")
    public Client ajouterClient(@Valid @RequestBody Client client){
        return iClient.ajouterClient(client);
    }
    @Operation(description="Récuperer la liste des clients")
    @GetMapping("/GetAll")
    public List<Client> getAll(){
        return iClient.recupererClients();
    }
    @Operation(description="Récuperer un client par son id")
    @GetMapping("/GetClient/{id}")
    public Client getClient(@PathVariable Long id){
        return iClient.recupererClientById(id);
    }
    @Operation(description="supprimer un client par son id")
    @DeleteMapping("/DeleteClient/{id}")
    public void deleteClient(@PathVariable Long id){
         iClient.supprimerClient(id);
    }
    @Operation(description="modifier un objet client dans la BD")
    @PutMapping("/EditClient/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client){
        client.setIdClient(id);
        return iClient.modifierClient(client);
    }

}
