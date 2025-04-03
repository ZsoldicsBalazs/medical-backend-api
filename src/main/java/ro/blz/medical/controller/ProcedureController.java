package ro.blz.medical.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blz.medical.domain.Procedure;
import ro.blz.medical.service.ProcedureService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/procedures")
public class ProcedureController {
    private final ProcedureService procedureService;


    @GetMapping
    public  ResponseEntity<List<Procedure>> getAllProcedures() {
        List<Procedure> procedureList = procedureService.findAll();
        return new ResponseEntity<>(procedureList, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Procedure> createProcedure(@RequestBody Procedure procedure) {
        Procedure createdProcedure = procedureService.save(procedure);
        return new ResponseEntity<>(createdProcedure, HttpStatus.CREATED);
    }
}
