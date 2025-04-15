package ro.blz.medical.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ro.blz.medical.dtos.DoctorDTO;
import ro.blz.medical.dtos.DoctorProcedureDTO;
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

    @GetMapping("/{id}/procedures")
    public ResponseEntity<List<DoctorProcedureDTO>> getAllProceduresByDoctorId(@PathVariable long id){
        return new ResponseEntity<>(doctorService.getAllProceduresByDoctor(id), HttpStatus.OK);
    }
    @PostMapping("/{id}/procedures")
    public ResponseEntity<?> addProcedure(@PathVariable long id, @RequestBody DoctorProcedureDTO doctorProcedureDTO){
//     TODO:  N+1 PROBLEM WHEN USING DTOMAPPER FUNCTION
        return new ResponseEntity<>(doctorService.addProcedureToDoctor(id,doctorProcedureDTO), HttpStatus.CREATED);
    }
    @PutMapping("/{id}/procedures")
    public ResponseEntity<?> updateProcedure(@PathVariable long id, @RequestBody DoctorProcedureDTO doctorProcedureDTO){
        DoctorProcedureDTO updatedProcedure = doctorService.updateProcedurePrice(id,doctorProcedureDTO);
        System.out.println("IN CONTROLLER UPDATED: --> " + updatedProcedure);
        return new ResponseEntity<>(updatedProcedure, HttpStatus.OK);
    }
    @DeleteMapping("/{id}/procedures/{pid}")
    public ResponseEntity<Boolean> deleteProcedure(@PathVariable long id, @PathVariable long pid, Authentication auth){
            boolean result = doctorService.deleteProcedureByDoctorIdAndProcedureId(id,pid);
        System.out.println(auth);
            return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
