package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.DTO.ReservationResponseDto;
import tn.esprit.autoloc.Entities.Reservation;

import java.util.List;
import java.util.Set;

public interface IReservation {
    Reservation ajouterReservation(Reservation re);
    Reservation modifierReservation(Reservation re);
    void supprimerReservation(Long idReservation);
    // Activité DTO - scénario 2 : consultation sous forme de DTO
    List<ReservationResponseDto> recupererReservations();
    List<ReservationResponseDto> recupererReservationsParClient(Long idClient);
    Set<Reservation> findReservations();
    Reservation recupererReservationById (Long idReservation);

}
