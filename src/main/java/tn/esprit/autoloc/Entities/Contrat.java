package tn.esprit.autoloc.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idContrat;

    @Column(nullable = false)
    @NotNull(message = "La date de signature est obligatoire")
    LocalDate dateSignature;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Le montant total est obligatoire")
    @Positive(message = "Le montant total doit être positif")
    BigDecimal montantTotal;

    @Column(nullable = false)
    boolean valide;

    @OneToOne(mappedBy = "contrat")
    @JsonIgnore
    Reservation reservation;

    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL)
    @JsonManagedReference("contrat-paiements")
    Set<Paiement> paiements;
}
