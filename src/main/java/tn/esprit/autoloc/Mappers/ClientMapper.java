package tn.esprit.autoloc.Mappers;

import org.mapstruct.Mapper;
import tn.esprit.autoloc.DTO.ClientRequestDto;
import tn.esprit.autoloc.DTO.ClientResponseDto;
import tn.esprit.autoloc.Entities.Client;

// MapStruct génère l'implémentation (ClientMapperImpl) à la compilation
@Mapper(componentModel = "spring")
public interface ClientMapper {

    // DTO reçu -> entité à persister (mêmes noms d'attributs : pas besoin de @Mapping)
    Client toEntity(ClientRequestDto dto);

    // entité -> DTO renvoyé par l'API
    ClientResponseDto toDto(Client client);
}
