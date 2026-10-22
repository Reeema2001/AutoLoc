package tn.esprit.autoloc.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.Reservation;
import tn.esprit.autoloc.Services.IReservation;

import java.util.List;

@RestController
@RequestMapping("/api/Reservation")
@RequiredArgsConstructor

public class ReservationController {
    private final IReservation iReservation;

    @PostMapping("/AddReservation")
    public Reservation ajouterReservation(@RequestBody Reservation reservation){
        return iReservation.ajouterReservation(reservation);
    }
    @GetMapping("/GetAll")
    public List<Reservation> getAll(){
        return iReservation.recupererReservations();
    }
    @GetMapping("/GetReservation/{id}")
    public Reservation getReservation(@PathVariable Long id){
        return iReservation.recupererReservationById(id);
    }
    @DeleteMapping("/DeleteReservation/{id}")
    public void deleteReservation(@PathVariable Long id){
         iReservation.supprimerReservation(id);
    }
    @PutMapping("/EditReservation")
    public Reservation updateReservation(@RequestBody Reservation reservation){
        return iReservation.modifierReservation(reservation);
    }

}
