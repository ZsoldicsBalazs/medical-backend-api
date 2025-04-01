package ro.blz.medical.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blz.medical.dtos.DoctorDTO;
import ro.blz.medical.service.DoctorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/medic")
public class DoctorController {

    private final DoctorService doctorService;


    @GetMapping()
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable long id){
        var doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    @PutMapping()
    public ResponseEntity<DoctorDTO> updateDoctor(@Valid @RequestBody DoctorDTO updatedDoctor){
        var doctorUpdated = doctorService.updateDoctor(updatedDoctor);
        return ResponseEntity.ok(doctorUpdated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDoctor(@PathVariable long id){
        doctorService.deleteDoctorById(id);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
