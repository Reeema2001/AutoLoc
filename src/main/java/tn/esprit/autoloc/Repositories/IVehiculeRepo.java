package tn.esprit.autoloc.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.Vehicule;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IVehiculeRepo extends JpaRepository<Vehicule,Long> {
    List<Vehicule> findByTarifJournalierGreaterThanEqual(BigDecimal tarifJournalier);
}
