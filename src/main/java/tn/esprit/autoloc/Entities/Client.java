package tn.esprit.autoloc.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import java.time.LocalDate;

// Activité DTO : plus de validators ici (@NotBlank, @Size, @Email...).
// Les règles de l'API sont dans ClientRequestDto ; l'entité ne garde que le mapping de la table (@Column).
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idClient;

    @Column(nullable = false, length = 50)
    String nom;

    @Column(nullable = false, length = 50)
    String prenom;

    // facultatif (Activité DTO)
    @Column(unique = true, length = 100)
    String email;

    // facultatif (Activité DTO)
    @Column(length = 20)
    String telephone;

    // absent du ClientRequestDto -> facultatif à la création
    @Column(unique = true, length = 30)
    String numPermis;

    // fixée par le service (LocalDate.now()) lors de l'ajout via le DTO
    @Column(nullable = false)
    LocalDate dateInscription;

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    @JsonIgnore
    Set<Reservation> reservations;
}
