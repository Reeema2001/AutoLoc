package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Exceptions.ResourceNotFoundException;
import tn.esprit.autoloc.Entities.Client;
import tn.esprit.autoloc.Repositories.IClientRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class ClientServiceImpl implements IClient {
    private final IClientRepo clientRepo;


    @Override
    public Client ajouterClient(Client cl) {
        return clientRepo.save(cl);
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

    @Override
    public Client recupererClientById(Long idClient) {
        // Avant (java:S3655 : Optional.get() appelé sans vérifier que la valeur existe)
        // return clientRepo.findById(idClient).get();
        return clientRepo.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client", idClient));
    }
}
