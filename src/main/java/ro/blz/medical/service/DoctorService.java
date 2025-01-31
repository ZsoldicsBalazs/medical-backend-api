package ro.blz.medical.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ro.blz.medical.dtos.DoctorDTO;
import ro.blz.medical.dtos.DoctorDTOMapper;
import ro.blz.medical.repository.DoctorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorDTOMapper doctorDTOMapper;
    @Autowired
    private ModelMapper modelMapper;


    public DoctorDTO getDoctorById(long id) {
//        EXAMPLE WITH MODEL MAPPER !
//        var dr = doctorRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));
//        return modelMapper.map(dr,DoctorDTO.class);

        return doctorRepository.findById(id).map(doctorDTOMapper).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream().map(doctorDTOMapper).collect(Collectors.toList());
    }


}
