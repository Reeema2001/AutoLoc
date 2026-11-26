package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.DTO.ReservationResponseDto;
import tn.esprit.autoloc.Exceptions.ResourceNotFoundException;
import tn.esprit.autoloc.Entities.Reservation;
import tn.esprit.autoloc.Mappers.ReservationMapper;
import tn.esprit.autoloc.Repositories.IClientRepo;
import tn.esprit.autoloc.Repositories.IReservationRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class ReservationServiceImpl implements IReservation {
    private final IReservationRepo reservationRepo;
    private final IClientRepo clientRepo;
    private final ReservationMapper reservationMapper;


    @Override
    public Reservation ajouterReservation(Reservation re) {
        return reservationRepo.save(re);
    }

    @Override
    public Reservation modifierReservation(Reservation re) {
        return reservationRepo.save(re);
    }

    @Override
    public void  supprimerReservation(Long idReservation) {
        reservationRepo.deleteById(idReservation);
    }

    // Activité DTO - scénario 2 : entités -> liste de DTO
    @Override
    public List<ReservationResponseDto> recupererReservations() {
        return reservationMapper.toDtoList(reservationRepo.findAll());
    }

    // Activité DTO - scénario 2 : réservations d'un client (requête JPQL)
    @Override
    public List<ReservationResponseDto> recupererReservationsParClient(Long idClient) {
        // erreur si le client n'existe pas (au lieu d'une liste vide trompeuse)
        if (!clientRepo.existsById(idClient)) {
            throw new ResourceNotFoundException("Client", idClient);
        }
        return reservationMapper.toDtoList(reservationRepo.findReservationsByClient(idClient));
    }

    @Override
    public Set<Reservation> findReservations() {
        return new HashSet<>(reservationRepo.findAll());
    }

    @Override
    public Reservation recupererReservationById(Long idReservation) {
        // Avant (java:S3655 : Optional.get() appelé sans vérifier que la valeur existe)
        // return reservationRepo.findById(idReservation).get();
        return reservationRepo.findById(idReservation)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", idReservation));
    }

}
