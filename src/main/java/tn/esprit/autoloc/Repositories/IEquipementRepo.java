package tn.esprit.autoloc.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.Equipement;

@Repository
public interface IEquipementRepo extends JpaRepository<Equipement,Long> {
}
