package tn.esprit.autoloc.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.Reservation;

import java.util.List;

@Repository
public interface IReservationRepo extends JpaRepository<Reservation,Long> {

    // Activité DTO - scénario 2 : réservations d'un client (JPQL avec jointure, Chapitre 10)
    @Query("SELECT r FROM Reservation r JOIN r.client c WHERE c.idClient = :idClient")
    List<Reservation> findReservationsByClient(@Param("idClient") Long idClient);
}
