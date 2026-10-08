package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Reservation;

import java.util.List;
import java.util.Set;

public interface IReservation {
    Reservation ajouterReservation(Reservation re);
    void supprimerReservation(long idReservation);
    List<Reservation> recupererReservations();
    Set<Reservation> findReservations();
    Reservation recupererReservationById (long idReservation);

}
