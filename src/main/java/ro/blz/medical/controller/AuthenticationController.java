package ro.blz.medical.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blz.medical.auth.AuthenticationResponse;
import ro.blz.medical.auth.UserAuthenticationRequest;
import ro.blz.medical.auth.UserRegistrationRequest;
import ro.blz.medical.config.JwtService;
import ro.blz.medical.service.AuthenticationService;
import ro.blz.medical.service.PatientRegistrationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final PatientRegistrationService patientRegistrationService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest registration) {
        return ResponseEntity.ok(patientRegistrationService.registerPatient(registration));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody UserAuthenticationRequest login) {

            AuthenticationResponse response = authenticationService.authenticate(login);
            return ResponseEntity.ok(response);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        boolean isValid = jwtService.validateToken(token);
        System.out.println(isValid);
        return ResponseEntity.ok(isValid);
    }

}
