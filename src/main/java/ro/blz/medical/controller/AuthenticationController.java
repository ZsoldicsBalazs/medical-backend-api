package ro.blz.medical.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blz.medical.auth.AuthenticationResponse;
import ro.blz.medical.auth.UserAuthenticationRequest;
import ro.blz.medical.auth.UserRegistrationRequest;
import ro.blz.medical.config.JwtService;
import ro.blz.medical.service.AuthenticationService;
import ro.blz.medical.service.PatientRegistrationService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final PatientRegistrationService patientRegistrationService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest registration) {
//        try {
            return ResponseEntity.ok(patientRegistrationService.registerPatient(registration));
//        }catch (UserAlreadyExistsException due){
//            System.out.println("Controller duplicate user exception: " + due.getMessage());
//            return new ResponseEntity<>(due.getMessage(),HttpStatus.BAD_REQUEST);
//        }
    }

//    @ExceptionHandler(value = UserAlreadyExistsException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public ErrorResponse handleDuplicateUserException(UserAlreadyExistsException duplicateUserException) {
//        return new ErrorResponse(HttpStatus.CONFLICT.value(),duplicateUserException.getMessage());
//    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody UserAuthenticationRequest login) {
        System.out.println("LOGIN CONTROLLER EMAIL : " + login.getEmail());
        AuthenticationResponse response= authenticationService.authenticate(login);
            return ResponseEntity.ok(response);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        boolean isValid = jwtService.validateToken(token);
        System.out.println(isValid);
        return ResponseEntity.ok(isValid);
    }

}
