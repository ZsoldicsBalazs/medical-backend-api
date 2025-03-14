package ro.blz.medical.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.blz.medical.repository.DoctorProcedureRepository;

@Service
@RequiredArgsConstructor
public class DoctorProcedureService {
    private final DoctorProcedureRepository drProcedureRepo;

}
