package ro.blz.medical.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blz.medical.dtos.AppointmentDTO;
import ro.blz.medical.dtos.AppointmentDetailsDTO;
import ro.blz.medical.dtos.AppointmentDetailsToDrDTO;
import ro.blz.medical.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.findAllAppointment());
    }
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok("Body");
    }

    @GetMapping("/{id}")
    public List<AppointmentDetailsDTO> findByPatientId(@PathVariable Long id) {
        return appointmentService.getAppointmentDetailsByPatientID(id);
    }
    @GetMapping("dr/{id}")
    public List<AppointmentDetailsToDrDTO> findByDrId(
            @PathVariable Long id,
            @RequestParam(required = false) String cnp,
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) String patientFirstName,
            @RequestParam(required = false) String appointmentDate
            ) {
        System.out.println(cnp);
        return appointmentService.getAppointmentDetailsByDoctor(id,cnp,patientName,patientFirstName,appointmentDate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return ResponseEntity.ok().build();
    }

}
