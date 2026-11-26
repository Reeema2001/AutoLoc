package tn.esprit.autoloc.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.autoloc.Entities.StatutReservation;

import java.time.LocalDate;

// DTO de sortie : une réservation "aplatie" avec l'essentiel du client et du véhicule
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDto {
    private Long idReservation;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutReservation statut;

    // depuis reservation.client
    private String nomClient;
    private String prenomClient;

    // depuis reservation.vehicule
    private String marque;
    private String modele;
    private String immatriculation;
}
