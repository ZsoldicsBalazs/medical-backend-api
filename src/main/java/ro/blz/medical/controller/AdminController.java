package ro.blz.medical.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blz.medical.auth.AuthenticationResponse;
import ro.blz.medical.domain.UserRegistrationRequest;
import ro.blz.medical.dtos.DoctorDTO;
import ro.blz.medical.dtos.PatientDTO;
import ro.blz.medical.dtos.PatientDTOMapper;
import ro.blz.medical.service.AuthenticationService;
import ro.blz.medical.service.DoctorService;
import ro.blz.medical.service.PatientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pacient")
@RequiredArgsConstructor
public class AdminController {

    private final DoctorService doctorService;
    private final AuthenticationService authenticationService;
    private final PatientService patientService;
    private final PatientDTOMapper patientDTOMapper;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        var allDoctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(allDoctors, HttpStatus.OK);
    }

    @PostMapping("/register-doctor")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody UserRegistrationRequest registration){
        return ResponseEntity.ok(authenticationService.registerDoctor(registration));
    }

    @GetMapping("/patients")
    public List<PatientDTO> getAllPatients(){
        return patientService.findAll().stream().map(patientDTOMapper).collect(Collectors.toList());
    }
}
