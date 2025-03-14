package ro.blz.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Procedure;
import ro.blz.medical.exceptions.ProcedureNotFoundException;
import ro.blz.medical.repository.ProcedureRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcedureService {

    private final ProcedureRepository procedureRepo;

    public List<Procedure> findAll() {
        return procedureRepo.findAll();
    }
    public Procedure findById(Long id) {
        return procedureRepo.findById(id).orElseThrow(()-> new ProcedureNotFoundException("Procedure with id " + id + " not found"));
    }
    public Procedure save(Procedure procedure) {
        if (procedureRepo.findByName(procedure.getName()) != null) {
            throw new RuntimeException("Procedure with name " + procedure.getName() + " already exists");
        }
        return procedureRepo.save(procedure);
    }




}
