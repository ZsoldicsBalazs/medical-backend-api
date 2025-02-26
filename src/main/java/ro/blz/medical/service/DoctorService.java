package ro.blz.medical.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.blz.medical.domain.Doctor;
import ro.blz.medical.dtos.DoctorDTO;
import ro.blz.medical.dtos.mapper.DoctorDTOMapperFunc;
import ro.blz.medical.exceptions.DoctorNotFoundException;
import ro.blz.medical.repository.DoctorRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorDTOMapperFunc doctorDTOMapperFunc;
    @Autowired
    private ModelMapper modelMapper;


    public DoctorDTO getDoctorById(long id) {
//        EXAMPLE WITH MODEL MAPPER !
//        var dr = doctorRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));
//        return modelMapper.map(dr,DoctorDTO.class);

        return doctorRepository.findById(id).map(doctorDTOMapperFunc).orElseThrow(() -> new DoctorNotFoundException("Doctor with id: "+ id +", not found"));
    }

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream().map(doctorDTOMapperFunc).collect(Collectors.toList());
    }

    public DoctorDTO deleteDoctorById(long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
        DoctorDTO deletedDoctor = modelMapper.map(doctor, DoctorDTO.class);
        doctorRepository.delete(doctor);
        return deletedDoctor;
    }


}
