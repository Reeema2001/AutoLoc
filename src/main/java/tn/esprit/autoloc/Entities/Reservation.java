package tn.esprit.autoloc.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idReservation;

    @Column(nullable = false)
    @NotNull(message = "La date de début est obligatoire")
    LocalDate dateDebut;

    @Column(nullable = false)
    @NotNull(message = "La date de fin est obligatoire")
    LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @NotNull(message = "Le statut est obligatoire")
    StatutReservation statut;

    @ManyToOne
    Client client;

    @ManyToOne
    Vehicule vehicule;

    @OneToOne
    Contrat contrat;
}
