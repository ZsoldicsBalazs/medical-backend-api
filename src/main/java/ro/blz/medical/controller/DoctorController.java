//package ro.blz.medical.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ro.blz.medical.domain.DoctorRegistrationRequest;
//import ro.blz.medical.dtos.DoctorDTO;
//import ro.blz.medical.service.DoctorService;
//
//
//@RestController
//@RequestMapping("/api/v1/doctor")
//public class DoctorController {
//
//    @Autowired
//    DoctorService doctorService;
//
//
//    @PostMapping
//    public ResponseEntity<?> saveDoctor(@RequestBody DoctorRegistrationRequest request) {
//        DoctorRegistrationRequest request1 = new DoctorRegistrationRequest("zblaziu","password","zsoldics","izabella","0748398337","izabella@gmail.com","Oncology");
//        doctorService.save(request1);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public DoctorDTO getDoctorById(@PathVariable long id) {
//        DoctorDTO drdto = null;
//        try {
//            drdto = doctorService.getDoctorById(id);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return drdto;
//    }
//
//}
