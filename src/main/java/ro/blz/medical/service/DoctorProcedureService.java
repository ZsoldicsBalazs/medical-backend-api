package ro.blz.medical.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.DoctorProcedure;
import ro.blz.medical.exceptions.ProcedureNotFoundException;
import ro.blz.medical.repository.DoctorProcedureRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorProcedureService {
    private final DoctorProcedureRepository drProcedureRepo;

    public List<DoctorProcedure> findProcedureByDoctorId(Long id) {
        List<DoctorProcedure> byDoctorId = drProcedureRepo.findByDoctorId(id);
        return byDoctorId;
    }

    @Transactional
    public DoctorProcedure save(DoctorProcedure doctorProcedure) {
        return drProcedureRepo.save(doctorProcedure);
    }
    @Transactional
    public DoctorProcedure updatePrice(Long procedureId, Double price) {
        DoctorProcedure doctorProcedure = drProcedureRepo.findById(procedureId).orElseThrow(() -> new ProcedureNotFoundException("Procedure not found"));
        doctorProcedure.setPrice(price);
        return doctorProcedure;
    }
    @Transactional
    public void deleteProcedure(Long procedureId) {
        drProcedureRepo.deleteById(procedureId);

    }

}
