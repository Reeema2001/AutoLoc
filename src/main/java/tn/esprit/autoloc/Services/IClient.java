package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Client;

import java.util.List;
import java.util.Set;

public interface IClient {
    Client ajouterClient(Client cl);
    Client modifierClient(Client cl);
    void supprimerClient(Long idClient);
    List<Client> recupererClients();
    Set<Client> findClients();
    Client recupererClientById (Long idClient);

}
