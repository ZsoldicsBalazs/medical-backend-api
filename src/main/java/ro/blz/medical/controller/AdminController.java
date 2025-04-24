package ro.blz.medical.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blz.medical.auth.DoctorRegistrationRequest;
import ro.blz.medical.auth.SecretaryRegistrationRequest;
import ro.blz.medical.dtos.DoctorDTO;
import ro.blz.medical.dtos.PatientDTO;
import ro.blz.medical.dtos.SecretaryDTO;
import ro.blz.medical.dtos.mapper.PatientDTOMapper;
import ro.blz.medical.service.AdminService;
import ro.blz.medical.service.DoctorService;
import ro.blz.medical.service.PatientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DoctorService doctorService;
    private final AdminService adminService;
    private final PatientService patientService;
    private final PatientDTOMapper patientDTOMapper;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        var allDoctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(allDoctors, HttpStatus.OK);
    }

    @PostMapping("/register-doctor")
    public ResponseEntity<DoctorDTO> register(@RequestBody @Valid DoctorRegistrationRequest registration) {
        return ResponseEntity.ok(adminService.registerDoctor(registration));
    }

    @GetMapping("/patients")
    public List<PatientDTO> getAllPatients() {
        return patientService.findAll().stream().map(patientDTOMapper).collect(Collectors.toList());
    }
    @PostMapping("/register-secretary")
    public ResponseEntity<SecretaryDTO> registerSecretary(@RequestBody SecretaryRegistrationRequest registration) {
        return ResponseEntity.ok(adminService.registerSecretary(registration));
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientDTO> findPatientById(@PathVariable Long id) {
        PatientDTO patientFound = patientService.findById(id);
        return new ResponseEntity<>(patientFound,HttpStatus.OK);
    }



}
