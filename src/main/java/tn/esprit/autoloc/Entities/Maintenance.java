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

public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idMaintenance;

    @Column(nullable = false)
    @NotNull(message = "La date de début est obligatoire")
    LocalDate dateDebut;

    LocalDate dateFin;

    @Column(length = 255)
    @Size(max = 255, message = "La description ne doit pas dépasser 255 caractères")
    String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    Vehicule vehicule;
}
