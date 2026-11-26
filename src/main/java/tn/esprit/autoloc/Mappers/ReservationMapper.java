package tn.esprit.autoloc.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.autoloc.DTO.ReservationResponseDto;
import tn.esprit.autoloc.Entities.Reservation;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    // idReservation, dateDebut, dateFin, statut : même nom -> copiés automatiquement
    // les autres champs viennent des relations Reservation -> Client et Reservation -> Vehicule
    @Mapping(target = "nomClient", source = "client.nom")
    @Mapping(target = "prenomClient", source = "client.prenom")
    @Mapping(target = "marque", source = "vehicule.marque")
    @Mapping(target = "modele", source = "vehicule.modele")
    @Mapping(target = "immatriculation", source = "vehicule.immatriculation")
    ReservationResponseDto toDto(Reservation reservation);

    // MapStruct réutilise toDto pour chaque élément de la liste
    List<ReservationResponseDto> toDtoList(List<Reservation> reservations);
}
