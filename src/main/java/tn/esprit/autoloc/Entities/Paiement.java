package tn.esprit.autoloc.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idPaiement;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    BigDecimal montant;

    @Column(nullable = false)
    @NotNull(message = "La date de paiement est obligatoire")
    LocalDate datePaiement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @NotNull(message = "Le mode de paiement est obligatoire")
    ModePaiement modePaiement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("contrat-paiements")
    Contrat contrat;
}
