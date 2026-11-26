package tn.esprit.autoloc.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// DTO de sortie : ce que l'API renvoie pour un client (pas d'email, de permis ni de réservations)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {
    private Long idClient;
    private String nom;
    private String prenom;
    private String telephone;
}
