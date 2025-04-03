package ro.blz.medical.service;


import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Doctor;
import ro.blz.medical.domain.DoctorProcedure;
import ro.blz.medical.domain.Procedure;
import ro.blz.medical.dtos.DoctorDTO;
import ro.blz.medical.dtos.DoctorProcedureDTO;
import ro.blz.medical.dtos.mapper.DoctorDTOMapperFunc;
import ro.blz.medical.dtos.mapper.DoctorProcedureDTOMapperFunction;
import ro.blz.medical.exceptions.DoctorNotFoundException;
import ro.blz.medical.exceptions.ProcedureNotFoundException;
import ro.blz.medical.repository.DoctorProcedureRepository;
import ro.blz.medical.repository.DoctorRepository;
import ro.blz.medical.repository.ProcedureRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorDTOMapperFunc doctorDTOMapperFunc;
    @Autowired
    private DoctorProcedureRepository drProcedureRepository;
    @Autowired
    private DoctorProcedureDTOMapperFunction drProcedureToDTO;
    @Autowired
    private ProcedureRepository procedureRepository;



    public DoctorDTO getDoctorById(long id) {
        return doctorRepository.findById(id).map(doctorDTOMapperFunc).orElseThrow(() -> new DoctorNotFoundException("Doctor with id: "+ id +", not found"));
    }

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream().map(doctorDTOMapperFunc).collect(Collectors.toList());
    }

    @Transactional
    public void deleteDoctorById(long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));
        doctorRepository.delete(doctor);
    }

    @Transactional
    public DoctorDTO updateDoctor(DoctorDTO updatedDoctor) {
        if (updatedDoctor == null){
            throw new NullPointerException("updatedDoctor is null");
        }
        Doctor doctor = doctorRepository.findById(updatedDoctor.id()).orElseThrow(()-> new DoctorNotFoundException("Doctor with id: " + updatedDoctor.id() + " not found"));
        doctor.setFirstName(updatedDoctor.firstName());
        doctor.setLastName(updatedDoctor.lastName());
        doctor.setEmail(updatedDoctor.email());
        doctor.setPhone(updatedDoctor.phone());
        return doctorDTOMapperFunc.apply(doctor);
    }

    @Transactional
    public List<DoctorProcedureDTO> getAllProceduresByDoctor(long id) {
        Doctor targetDoctor = doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException("Doctor with id: " + id + " not found"));
        return drProcedureRepository.findByDoctorId(id).stream().map(drProcedureToDTO).collect(Collectors.toList());
    }
    @Transactional
    public DoctorProcedureDTO addProcedureToDoctor(long id, DoctorProcedureDTO procedureDTO){
        Doctor existingDr = doctorRepository.findById(procedureDTO.doctor_id()).orElseThrow(()->new DoctorNotFoundException("Doctor with id: " + procedureDTO.doctor_id() + " not found"));
        Procedure existingProcedure = procedureRepository.findById(procedureDTO.procedure_id()).orElseThrow(()-> new ProcedureNotFoundException("Procedure with ID:" + procedureDTO.procedure_id() + " not found"));

        DoctorProcedure doctorProcedure = new DoctorProcedure();
        doctorProcedure.setDoctor(existingDr);
        doctorProcedure.setProcedure(existingProcedure);
        doctorProcedure.setPrice(procedureDTO.price());
        drProcedureRepository.save(doctorProcedure);
        return drProcedureToDTO.apply(doctorProcedure);
    }



}
