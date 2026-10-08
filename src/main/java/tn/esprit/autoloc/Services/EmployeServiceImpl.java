package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public void  supprimerEmploye(long idEmploye) {
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
    public Employe recupererEmployeById(long idEmploye) {
        return employeRepo.findById(idEmploye).get();
    }
}
