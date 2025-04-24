package ro.blz.medical.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blz.medical.domain.ConsultationRecord;
import ro.blz.medical.dtos.ConsultationRecordDTO;
import ro.blz.medical.dtos.PatientDTO;
import ro.blz.medical.service.ConsultationRecordService;
import ro.blz.medical.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("api/v1/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final ConsultationRecordService consultationRecordService;

    @GetMapping
    @RequestMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable Long id){
        return patientService.findById(id);
    }

    @PutMapping()
    ResponseEntity<PatientDTO> updatePatient(@RequestBody @Valid PatientDTO patientDTO) {
        PatientDTO patientUpdated = patientService.update(patientDTO);
        return new ResponseEntity<>(patientUpdated, HttpStatus.ACCEPTED);
    }
    @GetMapping
    @RequestMapping("/fulltext")
    public ResponseEntity<List<PatientDTO>> getAllPatientsBySearch(@RequestParam String search){
        return new ResponseEntity<>(patientService.searchByCNPPhoneFnameLname(search),HttpStatus.OK);
    }

    @GetMapping("/{id}/consultations")
    public ResponseEntity<List<ConsultationRecordDTO>> getAllConsultationsForPatient(@PathVariable Long id){
        return new ResponseEntity<>(patientService.getAllConsultationRecord(id),HttpStatus.OK);
    }
    @PostMapping("/{id}/consultation")
    public ResponseEntity<ConsultationRecordDTO> saveRecord(@RequestBody @Valid ConsultationRecordDTO consultationRecordDTO, @PathVariable Long id){
        return new ResponseEntity<>(consultationRecordService.createConsultationRecord(consultationRecordDTO),HttpStatus.CREATED);
    }
}
