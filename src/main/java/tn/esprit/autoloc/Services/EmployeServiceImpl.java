package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Exceptions.ResourceNotFoundException;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Repositories.IEmployeRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class EmployeServiceImpl implements IEmploye {
    private final IEmployeRepo employeRepo;


    @Override
    public Employe ajouterEmploye(Employe em) {
        return employeRepo.save(em);
    }

    @Override
    public Employe modifierEmploye(Employe em) {
        return employeRepo.save(em);
    }

    @Override
    public void  supprimerEmploye(Long idEmploye) {
        employeRepo.deleteById(idEmploye);
    }

    @Override
    public List<Employe> recupererEmployes() {
        return employeRepo.findAll();
    }

    @Override
    public Set<Employe> findEmployes() {
        return new HashSet<>(employeRepo.findAll());
    }

    @Override
    public Employe recupererEmployeById(Long idEmploye) {
        // Avant (java:S3655 : Optional.get() appelé sans vérifier que la valeur existe)
        // return employeRepo.findById(idEmploye).get();
        return employeRepo.findById(idEmploye)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", idEmploye));
    }
}
