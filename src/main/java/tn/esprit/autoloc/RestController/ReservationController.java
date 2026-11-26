package tn.esprit.autoloc.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.DTO.ReservationResponseDto;
import tn.esprit.autoloc.Entities.Reservation;
import tn.esprit.autoloc.Services.IReservation;

import java.util.List;

@RestController
@RequestMapping("/api/Reservation")
@RequiredArgsConstructor
@Tag(name="Gestion Reservation")
public class ReservationController {
    private final IReservation iReservation;

    @Operation(description="ajouter un objet réservation dans la BD")
    @PostMapping("/AddReservation")
    public Reservation ajouterReservation(@Valid @RequestBody Reservation reservation){
        return iReservation.ajouterReservation(reservation);
    }
    @Operation(description="Récuperer la liste des réservations")
    @GetMapping("/GetAll")
    public List<ReservationResponseDto> getAll(){
        return iReservation.recupererReservations();
    }
    @Operation(description="Récuperer les réservations d'un client")
    @GetMapping("/GetReservationsClient/{idClient}")
    public List<ReservationResponseDto> getReservationsClient(@PathVariable Long idClient){
        return iReservation.recupererReservationsParClient(idClient);
    }
    @Operation(description="Récuperer une réservation par son id")
    @GetMapping("/GetReservation/{id}")
    public Reservation getReservation(@PathVariable Long id){
        return iReservation.recupererReservationById(id);
    }
    @Operation(description="supprimer une réservation par son id")
    @DeleteMapping("/DeleteReservation/{id}")
    public void deleteReservation(@PathVariable Long id){
         iReservation.supprimerReservation(id);
    }
    @Operation(description="modifier un objet réservation dans la BD")
    @PutMapping("/EditReservation/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation reservation){
        reservation.setIdReservation(id);
        return iReservation.modifierReservation(reservation);
    }

}
