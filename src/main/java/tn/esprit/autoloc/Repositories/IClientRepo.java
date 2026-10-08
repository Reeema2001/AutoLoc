package tn.esprit.autoloc.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.Client;

import java.util.Optional;

@Repository
public interface IClientRepo extends JpaRepository<Client,Long> {
    Client save(Client client);
    Optional<Client> findById(Long id);
}
