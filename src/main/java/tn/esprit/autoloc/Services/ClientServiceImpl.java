package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.DTO.ClientRequestDto;
import tn.esprit.autoloc.DTO.ClientResponseDto;
import tn.esprit.autoloc.Exceptions.ResourceNotFoundException;
import tn.esprit.autoloc.Entities.Client;
import tn.esprit.autoloc.Mappers.ClientMapper;
import tn.esprit.autoloc.Repositories.IClientRepo;

import java.time.LocalDate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class ClientServiceImpl implements IClient {
    private final IClientRepo clientRepo;
    private final ClientMapper clientMapper;


    // Activité DTO - scénario 1 : DTO -> entité -> save -> DTO
    @Override
    public ClientResponseDto ajouterClient(ClientRequestDto dto) {
        Client client = clientMapper.toEntity(dto);   // DTO -> entité
        client.setDateInscription(LocalDate.now());   // fixée par le serveur, pas par l'API
        Client saved = clientRepo.save(client);
        return clientMapper.toDto(saved);             // entité -> DTO
    }

    @Override
    public Client modifierClient(Client cl) {
        return clientRepo.save(cl);
    }

    @Override
    public void  supprimerClient(Long idClient) {
        clientRepo.deleteById(idClient);
    }

    @Override
    public List<Client> recupererClients() {
        return clientRepo.findAll();
    }

    @Override
    public Set<Client> findClients() {
        return new HashSet<>(clientRepo.findAll());
    }

    // Activité DTO - scénario 1 : entité -> DTO
    @Override
    public ClientResponseDto recupererClientById(Long idClient) {
        // Avant (java:S3655 : Optional.get() appelé sans vérifier que la valeur existe)
        // return clientRepo.findById(idClient).get();
        Client client = clientRepo.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client", idClient));
        return clientMapper.toDto(client);
    }
}
