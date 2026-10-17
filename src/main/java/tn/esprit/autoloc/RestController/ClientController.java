package tn.esprit.autoloc.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Client;
import tn.esprit.autoloc.Services.IClient;

import java.util.List;

@RestController
@RequestMapping("/api/Client")
@RequiredArgsConstructor

public class ClientController {
    private final IClient iClient;

    @PostMapping("/AddClient")
    public Client ajouterClient(@RequestBody Client client){
        return iClient.ajouterClient(client);
    }
    @GetMapping("/GetAll")
    public List<Client> getAll(){
        return iClient.recupererClients();
    }
    @GetMapping("/GetClient/{id}")
    public Client getClient(@PathVariable Long id){
        return iClient.recupererClientById(id);
    }
    @DeleteMapping("/DeleteClient/{id}")
    public void deleteClient(@PathVariable Long id){
         iClient.supprimerClient(id);
    }
    @PutMapping("/EditClient")
    public Client updateClient(@RequestBody Client client){
        return iClient.modifierClient(client);
    }

}
