package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.DTO.ClientRequestDto;
import tn.esprit.autoloc.DTO.ClientResponseDto;
import tn.esprit.autoloc.Entities.Client;

import java.util.List;
import java.util.Set;

public interface IClient {
    // Activité DTO : l'ajout reçoit un ClientRequestDto et renvoie un ClientResponseDto
    ClientResponseDto ajouterClient(ClientRequestDto dto);
    Client modifierClient(Client cl);
    void supprimerClient(Long idClient);
    List<Client> recupererClients();
    Set<Client> findClients();
    // Activité DTO : la consultation renvoie un ClientResponseDto
    ClientResponseDto recupererClientById (Long idClient);

}
