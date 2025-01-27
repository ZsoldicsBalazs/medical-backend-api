package ro.blz.medical.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.blz.medical.auth.AuthenticationResponse;
import ro.blz.medical.auth.AuthenticationService;
import ro.blz.medical.auth.DoctorAuthenticationRequest;
import ro.blz.medical.domain.DoctorRegistrationRequest;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody DoctorRegistrationRequest registration){
        return ResponseEntity.ok(authenticationService.register(registration));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody DoctorAuthenticationRequest login){
        return ResponseEntity.ok(authenticationService.authenticate(login));
    }

}
